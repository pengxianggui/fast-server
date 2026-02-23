package io.github.pengxianggui.server.common.res;

import io.github.pengxianggui.server.common.ex.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理。确保返回前端指定统一格式
 *
 * @author pengxg
 * @date 2026/2/20 23:13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public HttpResult handleAccessDenied(AccessDeniedException e) {
        return HttpResult.fail(403, "权限不足", e);
    }

    @ExceptionHandler(AuthenticationException.class)
    public HttpResult handleAuthenticationException(AuthenticationException e) {
        return HttpResult.fail(401, "认证失败", e);
    }

    @ExceptionHandler(BizException.class)
    public HttpResult handleBizException(BizException e) {
        return HttpResult.fail(-1, e.getMessage(), e.getEx());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public HttpResult handle404(NoHandlerFoundException e) {
        return HttpResult.fail(404, "接口地址不存在", e);
    }

    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        log.error("服务器错误", e);
        return HttpResult.fail("服务器错误", e);
    }
}
