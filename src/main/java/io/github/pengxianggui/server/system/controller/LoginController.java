package io.github.pengxianggui.server.system.controller;

import cn.hutool.core.lang.Assert;
import io.github.pengxianggui.server.auth.LoginUser;
import io.github.pengxianggui.server.auth.SecurityUtil;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.common.i18n.I18nUtil;
import io.github.pengxianggui.server.system.model.dto.*;
import io.github.pengxianggui.server.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录登出
 *
 * @author pengxg
 * @date 2026/2/20 22:51
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/captcha")
    public void getCaptcha() {
        // TODO 验证码生成
    }

    /**
     * 登录
     *
     * @param req
     * @return
     */
    @PostMapping("/login")
    public LoginResultDTO login(@Validated @RequestBody LoginRequest req) {
        return userService.login(req);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public LoginInfoDTO userInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        Assert.isTrue(loginUser != null, () -> new BizException(I18nUtil.get("auth.credential_expired")));
        return new LoginInfoDTO(SecurityUtil.getLoginUser());
    }

    /**
     * 更新用户信息
     *
     * @param id  用户id
     * @param req 更新表单
     */
    @PostMapping("{id}/update")
    public void userUpdate(@PathVariable Long id, @RequestBody UserUpdateReq req) {
        userService.updateUser(id, req);
    }

    /**
     * 变更密码
     *
     * @param id
     * @param req
     */
    @PostMapping("{id}/change-password")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordReq req) {
        userService.changePassword(id, req);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout() {
        // TODO jwt 无状态如何实现登出？黑名单？
    }
}
