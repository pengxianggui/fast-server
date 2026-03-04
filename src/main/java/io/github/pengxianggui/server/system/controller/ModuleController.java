package io.github.pengxianggui.server.system.controller;

import cn.hutool.core.lang.tree.Tree;
import io.github.pengxianggui.server.system.service.ModuleService;
import io.github.pengxianggui.server.system.model.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

import java.util.List;

@RestController
@RequestMapping("module")
public class ModuleController extends BaseController<Module> {

    @Autowired
    private ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        super(moduleService, Module.class);
    }

    /**
     * 获取模块树
     *
     * @param moduleId    以此为根节点。默认为0(系统根模块节点id固定为0)
     * @param includeSelf 是否包含根节点自身(默认true)
     * @return
     */
    @GetMapping("tree")
    public List<Tree<Long>> getModuleTree(@RequestParam(defaultValue = "0") Long moduleId,
                                          @RequestParam(defaultValue = "true") Boolean includeSelf) {
        return moduleService.getModuleTree(moduleId, includeSelf);
    }
}
