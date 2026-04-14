package io.github.pengxianggui.server.auth;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.common.i18n.I18nUtil;
import io.github.pengxianggui.server.system.model.entity.Auth;
import io.github.pengxianggui.server.system.model.entity.Role;
import io.github.pengxianggui.server.system.model.entity.User;
import io.github.pengxianggui.server.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * JWT认证过滤器：从jwt中解析用户信息并存入spring security上下文
 *
 * @author pengxg
 * @date 2026/2/20 22:31
 */
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private UserService userService;

    public JwtAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (token.toLowerCase().startsWith("bearer ")) {
                token = token.substring(7); // 截取掉 "Bearer "
            }
            if (JwtUtils.verify(token)) {
                String username = JwtUtils.getUsername(token);
                User user = userService.getByUsername(username);
                Assert.isTrue(user != null, () -> new BizException(I18nUtil.get("auth.credential_expired"),
                        new UsernameNotFoundException(I18nUtil.get("auth.username_not_found", username))));
                List<Role> roles = userService.getRolesOfUser(user.getId());
                List<Auth> auths = userService.getAuthsOfUser(user.getId());
                LoginUser loginUser = new LoginUser(user, roles, auths);
                // 构建认证信息并存入上下文
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        loginUser,
                        user.getPassword(),
                        loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.error("Parse JWT error", e);
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
