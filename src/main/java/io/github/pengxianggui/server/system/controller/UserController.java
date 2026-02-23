package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.service.UserService;
import io.github.pengxianggui.server.system.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<User>{

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super(userService, User.class);
    }

}
