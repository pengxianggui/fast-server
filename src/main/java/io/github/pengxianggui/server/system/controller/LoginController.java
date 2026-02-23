package io.github.pengxianggui.server.system.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import io.github.pengxianggui.server.auth.JwtUtils;
import io.github.pengxianggui.server.auth.LoginUser;
import io.github.pengxianggui.server.auth.SecurityUtil;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.system.model.dto.LoginRequest;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.model.entity.User;
import io.github.pengxianggui.server.system.model.vo.LoginResultVO;
import io.github.pengxianggui.server.system.model.vo.LoginInfoVO;
import io.github.pengxianggui.server.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public LoginResultVO login(@Validated @RequestBody LoginRequest req) {
        User user = userService.getByUsername(req.getUsername());
        Assert.isTrue(user != null, () -> new BizException("用户名或密码错误",
                new UsernameNotFoundException(StrUtil.format("用户名不存在:{}", req.getUsername()))));
        Assert.isTrue(BCrypt.checkpw(req.getPassword(), user.getPassword()), "密码错误");
        List<Role> roles = userService.getRolesOfUser(user.getId());
        List<Auth> auths = userService.getAuthsOfUser(user.getId());
        String token = JwtUtils.createToken(user.getId(), req.getUsername());
        return new LoginResultVO(
                token,
                LoginInfoVO.builder()
                        .id(user.getId())
                        .username(req.getUsername())
                        .roles(roles.stream().map(Role::getCode).collect(Collectors.toSet()))
                        .auths(auths.stream().map(Auth::getCode).collect(Collectors.toSet()))
                        .build()
        );
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public LoginInfoVO userInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        return LoginInfoVO.builder()
                .id(loginUser.getId())
                .username(loginUser.getUsername())
                .roles(loginUser.getRoles())
                .auths(loginUser.getPermissions())
                .build();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout() {
        // TODO jwt 无状态如何实现登出？黑名单？
    }
}
