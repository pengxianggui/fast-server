package io.github.pengxianggui.server.system.controller;

import io.github.pengxianggui.crud.query.PagerQuery;
import io.github.pengxianggui.crud.query.PagerView;
import io.github.pengxianggui.server.system.model.vo.AuthPageVO;
import io.github.pengxianggui.server.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.pengxianggui.crud.BaseController;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController<AuthPageVO> {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        super(authService, AuthPageVO.class);
    }

    @PostMapping("page")
    @Override
    public PagerView<AuthPageVO> page(@RequestBody @Validated PagerQuery query) {
        return authService.getPageVO(query);
    }
}
