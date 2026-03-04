package io.github.pengxianggui.server.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.system.model.entity.Module;
import io.github.pengxianggui.server.system.mapper.ModuleMapper;
import io.github.pengxianggui.server.system.model.vo.ModuleDTO;
import io.github.pengxianggui.server.system.service.ModuleService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper, Module> implements ModuleService {

    @Override
    public List<Tree<Long>> getModuleTree(Long moduleId, boolean includeSelf) {
        // 避免递归，直接全查
        List<ModuleDTO> modules = list().stream().map(ModuleDTO::new).collect(Collectors.toList());
        Long rootId = moduleId;
        if (includeSelf) {
            ModuleDTO root = modules.stream().filter(m -> m.getId().equals(moduleId))
                    .findFirst().orElseThrow(() -> new BizException("模块不存在:{}", moduleId));
            rootId = root.getParentId();
            modules.add(root);
        }
        return TreeUtil.build(modules, rootId, (moduleDTO, treeNode) -> {
            treeNode.setId(moduleDTO.getId());
            treeNode.setParentId(moduleDTO.getParentId());
            treeNode.setName(moduleDTO.getName());
            treeNode.setWeight(moduleDTO.getWeight());
            treeNode.putExtra("description", moduleDTO.getDescription());
        });
    }

    @Override
    public List<Module> getByParentId(Long parentId) {
        if (parentId == null) return new ArrayList<>();
        Wrapper<Module> queryWrapper = new LambdaQueryWrapper<Module>().eq(Module::getParentId, parentId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Module> getTreeAsFlat(Long id) {
        // 查询所有数据（权限模块数据量通常不大，内存处理效率最高）
        List<Module> allModules = this.list();
        // 递归筛选出指定节点及其所有子孙
        Set<Long> resultIds = new HashSet<>();
        findAllChildIds(id, allModules, resultIds);
        // 根据计算出的所有 ID 过滤结果
        return allModules.stream()
                .filter(m -> resultIds.contains(m.getId()))
                .collect(Collectors.toList());
    }

    /**
     * 递归寻找子节点 ID
     */
    private void findAllChildIds(Long parentId, List<Module> allModules, Set<Long> resultIds) {
        // 防止重复处理
        if (!resultIds.add(parentId)) {
            return;
        }
        // 找出当前节点的所有子节点
        allModules.stream()
                .filter(m -> parentId.equals(m.getParentId()))
                .forEach(m -> findAllChildIds(m.getId(), allModules, resultIds));
    }
}
