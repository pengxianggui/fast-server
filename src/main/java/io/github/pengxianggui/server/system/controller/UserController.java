package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.model.dto.RoleBindDTO;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.service.UserService;
import io.github.pengxianggui.server.system.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.pengxianggui.crud.BaseController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super(userService, User.class);
    }

    /**
     * 获取用户拥有的角色列表
     *
     * @param id 用户ID
     * @return
     */
    @GetMapping("/{id}/roles")
    public List<Role> getRoles(@PathVariable Long id) {
        return userService.getRolesOfUser(id);
    }

    /**
     * 获取用户拥有的角色列表
     *
     * @param id 用户ID
     * @return
     */
    @PostMapping("/{id}/roles")
    public boolean setRoles(@PathVariable Long id, @Valid @RequestBody RoleBindDTO req) {
        return userService.setRoleForUser(id, req.getRoleIds());
    }


}
