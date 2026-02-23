package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.server.system.service.AuthService;
import io.github.pengxianggui.server.system.model.entity.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController<Auth>{

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        super(authService, Auth.class);
    }

}
