package io.github.pengxianggui.server.system.service;

import cn.hutool.core.lang.tree.Tree;
import io.github.pengxianggui.crud.BaseService;
import io.github.pengxianggui.server.system.model.entity.Module;

import java.util.List;

public interface ModuleService extends BaseService<Module> {

    /**
     * 获取模块树
     *
     * @param moduleId    模块ID
     * @param includeSelf 是否包含moduleId自己，若包括, 则返回的是一棵树, 否则可能是moduleId下的多棵子树
     * @param includeAuth 模块树上是否挂权限节点
     * @return
     */
    List<Tree<Long>> getModuleTree(Long moduleId, boolean includeSelf, boolean includeAuth);

    List<Module> getByParentId(Long parentId);

    /**
     * 根据指定模块id对应的模块树(拍平)
     *
     * @param id 模块ID
     * @return
     */
    List<Module> getTreeAsFlat(Long id);
}