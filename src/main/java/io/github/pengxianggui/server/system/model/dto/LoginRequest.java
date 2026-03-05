package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author pengxg
 * @date 2026/2/20 22:53
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    @NotBlank(message = "{auth.username_required}")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "{auth.password_required}")
    private String password;
}
