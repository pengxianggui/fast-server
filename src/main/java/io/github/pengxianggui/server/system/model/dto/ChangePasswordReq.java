package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengxg
 * @date 2026/4/14 14:14
 */
@NoArgsConstructor
@Data
public class ChangePasswordReq {
    private String oldPassword;
    private String newPassword;
}
