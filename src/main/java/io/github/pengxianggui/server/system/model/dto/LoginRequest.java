package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;

/**
 * @author pengxg
 * @date 2026/2/20 22:53
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
