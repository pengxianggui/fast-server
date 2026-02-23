package io.github.pengxianggui.server.system.model.vo;

import lombok.Data;

/**
 * 登录结果VO
 *
 * @author pengxg
 * @date 2026/2/20 22:55
 */
@Data
public class LoginResultVO {
    private String token;
    private LoginInfoVO userInfo;

    public LoginResultVO(String token, LoginInfoVO userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }
}
