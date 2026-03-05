package io.github.pengxianggui.server.common.res;

import io.github.pengxianggui.server.common.i18n.I18nUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义错误控制器。确保路径不存在/容器级错误时，返回给前端指定格式的响应({@link HttpResult}
 *
 * @author pengxg
 * @date 2026/2/23 00:01
 * @see HttpResult
 */
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public HttpResult handleError(HttpServletRequest request, HttpServletResponse response) {
        // 1. 获取原始的 HTTP 状态码
        Object status = request.getAttribute("javax.servlet.error.status_code");
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

        if (status != null) {
            code = Integer.parseInt(status.toString());
        }

        // 2. 根据状态码返回友好的提示
        if (code == HttpStatus.NOT_FOUND.value()) {
            return HttpResult.fail(404, I18nUtil.get("error.resource_not_found"));
        } else if (code == HttpStatus.FORBIDDEN.value()) {
            return HttpResult.fail(403, I18nUtil.get("error.forbidden"));
        } else if (code == HttpStatus.UNAUTHORIZED.value()) {
            return HttpResult.fail(401, I18nUtil.get("error.unauthorized"));
        }

        return HttpResult.fail(code, I18nUtil.get("common.system_error"));
    }
}
