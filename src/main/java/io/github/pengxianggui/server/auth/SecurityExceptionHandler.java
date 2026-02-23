package io.github.pengxianggui.server.auth;

import cn.hutool.json.JSONUtil;
import io.github.pengxianggui.server.common.res.HttpResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring security认证&鉴权异常处理器
 *
 * @author pengxg
 * @date 2026/2/21 09:00
 */
@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        renderJson(response, HttpResult.fail(401, "认证失败，请先登录", authException));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        renderJson(response, HttpResult.fail(403, "抱歉，您没有权限操作此功能", accessDeniedException));
    }

    private void renderJson(HttpServletResponse response, HttpResult result) throws IOException {
        response.setStatus(200); // 建议业务 code 返回 403/401，但 HTTP 状态保持 200 方便前端拦截
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
