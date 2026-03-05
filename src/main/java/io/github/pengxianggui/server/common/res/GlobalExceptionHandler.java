package io.github.pengxianggui.server.common.res;

import io.github.pengxianggui.server.common.ex.BizException;
import io.github.pengxianggui.server.common.i18n.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return HttpResult.fail(403, I18nUtil.get("common.permission_denied"), e);
    }

    @ExceptionHandler(AuthenticationException.class)
    public HttpResult handleAuthenticationException(AuthenticationException e) {
        return HttpResult.fail(401, I18nUtil.get("security.auth_required"), e);
    }

    @ExceptionHandler(BizException.class)
    public HttpResult handleBizException(BizException e) {
        return HttpResult.fail(-1, e.getMessage(), e.getEx());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError() == null
                ? I18nUtil.get("common.fail")
                : e.getBindingResult().getFieldError().getDefaultMessage();
        return HttpResult.fail(400, message, e);
    }

    @ExceptionHandler(BindException.class)
    public HttpResult handleBindException(BindException e) {
        String message = e.getFieldError() == null
                ? I18nUtil.get("common.fail")
                : e.getFieldError().getDefaultMessage();
        return HttpResult.fail(400, message, e);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public HttpResult handle404(NoHandlerFoundException e) {
        return HttpResult.fail(404, I18nUtil.get("error.api_not_found"), e);
    }

    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e) {
        log.error(I18nUtil.get("common.server_error"), e);
        return HttpResult.fail(I18nUtil.get("common.server_error"), e);
    }
}
