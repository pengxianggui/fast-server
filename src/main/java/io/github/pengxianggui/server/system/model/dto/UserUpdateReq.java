package io.github.pengxianggui.server.system.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengxg
 * @date 2026/4/14 14:11
 */
@NoArgsConstructor
@Data
public class UserUpdateReq {
    private String phone;
    private String nickName;
    private String realName;
    private String idCard;
    private String email;
}
