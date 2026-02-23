package io.github.pengxianggui.server.auth;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengxg
 * @date 2026/2/20 22:17
 */
public class JwtUtils {
    // TODO 使用环境变量或者配置文件管理密钥和过期天数
    private static final byte[] KEY = "your-secret-key-for-2026".getBytes();
    private static final int EXPIRE_DAYS = 7;

    /**
     * 创建token
     *
     * @param userId   用户id
     * @param username 用户名
     * @return
     */
    public static String createToken(Long userId, String username) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        payload.put("username", username);
        payload.put(JWT.EXPIRES_AT, DateUtil.offsetDay(new Date(), EXPIRE_DAYS));
        return JWTUtil.createToken(payload, KEY);
    }

    /**
     * 验证token有效性
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        return JWTUtil.verify(token, KEY);
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return (String) JWTUtil.parseToken(token).getPayload("username");
    }
}
