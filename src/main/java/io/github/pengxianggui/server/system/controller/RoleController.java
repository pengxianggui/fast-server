package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.service.RoleService;
import io.github.pengxianggui.server.system.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role>{

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService, Role.class);
    }

}
