package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.service.AuthService;
import io.github.pengxianggui.server.system.service.RoleService;
import io.github.pengxianggui.server.system.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role> {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    public RoleController(RoleService roleService) {
        super(roleService, Role.class);
    }

    /**
     * 获取角色拥有的权限列表
     *
     * @param id 角色ID
     * @return
     */
    @GetMapping("/{id}/auths")
    public List<Auth> getAuths(@PathVariable Long id) {
        return authService.getAuthsOfRole(id);
    }
}
