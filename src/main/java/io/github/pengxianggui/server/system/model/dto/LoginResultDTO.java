package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;

/**
 * 登录结果
 *
 * @author pengxg
 * @date 2026/2/20 22:55
 */
@Data
public class LoginResultDTO {
    private String token;
    private LoginInfoDTO userInfo;

    public LoginResultDTO(String token, LoginInfoDTO userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }
}
