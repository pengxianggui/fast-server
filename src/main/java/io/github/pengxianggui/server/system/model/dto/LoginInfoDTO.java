package io.github.pengxianggui.server.system.model.dto;

import io.github.pengxianggui.server.auth.LoginUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author pengxg
 * @date 2026/2/23 14:32
 */
@NoArgsConstructor
@Data
public class LoginInfoDTO {
    private Long id;
    private String username;
    private String phone;
    private String nickName;
    private String realName;
    private String idCard;
    private String email;
    private Set<String> roles;
    private Set<String> auths;

    public LoginInfoDTO(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.username = loginUser.getUsername();
        this.phone = loginUser.getPhone();
        this.nickName = loginUser.getNickName();
        this.realName = loginUser.getRealName();
        this.idCard = loginUser.getIdCard();
        this.email = loginUser.getEmail();
        this.roles = loginUser.getRoles();
        this.auths = loginUser.getPermissions();
    }
}
