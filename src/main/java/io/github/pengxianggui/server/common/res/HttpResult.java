package io.github.pengxianggui.server.common.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义响应结果(0-成功)
 *
 * @author pengxg
 * @date 2026/2/20 23:03
 */
@NoArgsConstructor
@Getter
public class HttpResult<T> {
    private int code;
    private String message;
    private T data;
    private String exMessage;
//    private String requestUri; TODO

    private HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<>(0, "成功", data);
    }

    public static HttpResult fail() {
        return HttpResult.fail("失败");
    }

    public static HttpResult fail(String msg) {
        return HttpResult.fail(500, msg);
    }

    public static HttpResult fail(int code, String msg) {
        return HttpResult.fail(code, msg, null);
    }

    public static HttpResult fail(String msg, Exception ex) {
        return HttpResult.fail(500, msg, ex);
    }

    public static HttpResult fail(int code, String msg, Exception ex) {
        HttpResult result = new HttpResult(code, msg, null);
        result.exMessage = ex == null ? null : ex.getMessage();
        return result;
    }
}
