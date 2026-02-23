package io.github.pengxianggui.server.common.res;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应结果包装器。
 * <p>
 * 自动将接口返回值包装为HttpResult，针对正常响应的接口，包装为{@link HttpResult}格式
 *
 * @author pengxg
 * @date 2026/2/20 23:35
 */
@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查返回类型是否已经是HttpResult，如果是则不再包装
        return !returnType.getParameterType().equals(HttpResult.class);
    }

    @Override
    @NonNull
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        // 如果媒体类型不是json，则不进行包装
        if (!selectedContentType.equals(MediaType.APPLICATION_JSON)
                && !selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
            return body;
        }

        // 如果返回值已经是HttpResult类型，直接返回（理论上不应该发生，因为supports方法已过滤）
        if (body instanceof HttpResult) {
            return body;
        }

        // 如果返回值为null，则包装为成功的空数据响应
        if (body == null) {
            return HttpResult.success(null);
        }

        // 否则将返回值包装为HttpResult
        return HttpResult.success(body);
    }
}