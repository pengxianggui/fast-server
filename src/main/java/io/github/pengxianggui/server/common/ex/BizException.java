package io.github.pengxianggui.server.common.ex;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 业务异常。可通知给客户端的异常
 *
 * @author pengxg
 * @date 2026/2/22 22:55
 */
public class BizException extends RuntimeException {
    @Getter
    private Exception ex;

    public BizException(String message, Object... args) {
        super(StrUtil.format(message, args));
    }

    /**
     * @param message 提示给客户端的信息
     * @param ex      原始异常
     */
    public BizException(String message, Exception ex) {
        super(message);
        this.ex = ex;
    }
}
