package io.github.pengxianggui.server.auth;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author pengxg
 * @date 2026/2/20 23:59
 */
public class SecurityUtil {

    /**
     * 获取当前登录用户信息
     */
    public static LoginUser getLoginUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (ObjectUtil.isNull(authentication) || !authentication.isAuthenticated()) {
                return null;
            }
            Object principal = authentication.getPrincipal();
            if (principal instanceof LoginUser) {
                return (LoginUser) principal;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 获取登录用户ID
     */
    public static Long getLoginUserId() {
        LoginUser user = getLoginUser();
        return ObjectUtil.isNotNull(user) ? user.getId() : null;
    }

    /**
     * 获取登录用户名称
     *
     * @return
     */
    public static String getLoginUsername() {
        LoginUser user = getLoginUser();
        return ObjectUtil.isNotNull(user) ? user.getUsername() : null;
    }
}
