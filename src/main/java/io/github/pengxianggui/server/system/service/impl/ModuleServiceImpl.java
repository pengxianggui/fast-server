package io.github.pengxianggui.server.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.pengxianggui.crud.BaseServiceImpl;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.system.model.dto.AuthTreeNodeDTO;
import io.github.pengxianggui.server.system.model.entity.Module;
import io.github.pengxianggui.server.system.mapper.ModuleMapper;
import io.github.pengxianggui.server.system.model.dto.ModuleTreeNodeDTO;
import io.github.pengxianggui.server.system.service.AuthService;
import io.github.pengxianggui.server.system.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper, Module> implements ModuleService {
    @Autowired
    private AuthService authService;

    @Override
    public List<Tree<Long>> getModuleTree(Long moduleId, boolean includeSelf, boolean includeAuth) {
        // 避免递归，直接全查
        List<TreeNode<Long>> nodes = list().stream().map(ModuleTreeNodeDTO::new).collect(Collectors.toList());
        Long rootId = moduleId;
        if (includeSelf) {
            TreeNode<Long> root = nodes.stream().filter(m -> m.getId().equals(moduleId))
                    .findFirst().orElseThrow(() -> new BizException("模块不存在:{}", moduleId));
            rootId = root.getParentId();
            nodes.add(root);
        }
        if (includeAuth) {
            List<TreeNode<Long>> auths = authService.list().stream().map(AuthTreeNodeDTO::new)
                    .collect(Collectors.toList());
            nodes.addAll(auths);
        }
        return TreeUtil.build(nodes, rootId, (node, treeNode) -> {
            treeNode.setId(node.getId());
            treeNode.setParentId(node.getParentId());
            treeNode.setName(node.getName());
            treeNode.setWeight(node.getWeight());
            if (node instanceof ModuleTreeNodeDTO) {
                treeNode.putExtra("description", ((ModuleTreeNodeDTO) node).getDescription());
                treeNode.putExtra("isAuth", false);
            } else if (node instanceof AuthTreeNodeDTO) {
                treeNode.putExtra("description", ((AuthTreeNodeDTO) node).getDescription());
                treeNode.putExtra("isAuth", true);
            }
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
