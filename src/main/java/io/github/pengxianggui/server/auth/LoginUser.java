package io.github.pengxianggui.server.auth;

import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录用户模型。
 * <p>
 * 用于在 Security 上下文中传递用户信息
 *
 * @author pengxg
 * @date 2026/2/20 22:03
 */
@Data
public class LoginUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String nickName;
    private String realName;
    private String idCard;
    private String email;
    /**
     * 角色编码
     */
    private Set<String> roles;
    /**
     * 权限编码
     */
    private Set<String> permissions;

    public LoginUser(User user, List<Role> roles, List<Auth> auths) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.nickName = user.getNickName();
        this.realName = user.getRealName();
        this.idCard = user.getIdCard();
        this.email = user.getEmail();
        this.roles = roles.stream().map(Role::getCode).collect(Collectors.toSet());
        this.permissions = auths.stream().map(Auth::getCode).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = roles.stream()
                // doubt spring security建议角色名带ROLE_前缀吗？
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toSet());

        authorities.addAll(permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 当前系统未设计用户有效期
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 当前系统未设计用户锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 当前系统未设计密码有效期
    }

    @Override
    public boolean isEnabled() {
        return true; // 当前系统未设计用户禁用
    }
}
