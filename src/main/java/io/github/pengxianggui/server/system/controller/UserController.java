package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.service.UserService;
import io.github.pengxianggui.server.system.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

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
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/{id}/roles")
    public List<Role> getRoles(@PathVariable Long userId) {
        return userService.getRolesOfUser(userId);
    }
}
