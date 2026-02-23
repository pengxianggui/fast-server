package io.github.pengxianggui.server.auth;

import com.google.common.collect.Sets;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义权限校验切面
 *
 * @author pengxg
 * @date 2026/2/20 22:38
 * @see Authorize
 */
@Aspect
@Component
public class AuthorizeAspect {

    @Before("@annotation(authorize)")
    public void doAuthorize(Authorize authorize) {
        // 获取当前登录用户
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || "anonymousUser".equals(auth.getPrincipal())) {
            throw new AccessDeniedException("未登录或 Token 已失效");
        }

        // 假设用户信息已通过 Filter 存入，取出其角色和权限集
        // 此处应为 LoginUser，为演示直接从 authorities 取字符串
        Set<String> userAuthorities = auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());

        // 判定角色鉴权
        if (authorize.role().length > 0) {
            Set<String> requiredRoles = Sets.newHashSet(authorize.role());
            // 注意：LoginUser 中角色带了 ROLE_ 前缀
            boolean hasRole = requiredRoles.stream().anyMatch(r -> userAuthorities.contains("ROLE_" + r));
            if (!hasRole) throw new AccessDeniedException("无对应角色访问权限");
            return; // 满足角色即可放行
        }

        // 4. 判定权限鉴权
        if (authorize.value().length > 0) {
            Set<String> requiredAuths = Sets.newHashSet(authorize.value());
            boolean hasAuth = requiredAuths.stream().anyMatch(userAuthorities::contains);
            if (!hasAuth) throw new AccessDeniedException("无对应接口操作权限");
            return;
        }

        // 5. 若 role 和 value 都没配置，运行到这里说明已登录，直接放行
    }
}
