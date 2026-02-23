package io.github.pengxianggui.server.common.res;

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
            return HttpResult.fail(404, "您访问的资源不存在");
        } else if (code == HttpStatus.FORBIDDEN.value()) {
            return HttpResult.fail(403, "拒绝访问");
        } else if (code == HttpStatus.UNAUTHORIZED.value()) {
            return HttpResult.fail(401, "未授权，请先登录");
        }

        return HttpResult.fail(code, "系统异常，请稍后再试");
    }
}
