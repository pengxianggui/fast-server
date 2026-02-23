package io.github.pengxianggui.server.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengxg
 * @date 2026/2/22 15:52
 */
@Slf4j
public class SpELUtil {

    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    private static final Map<String, Expression> CACHE = new ConcurrentHashMap<>();


    /**
     * 解析spEL表达式，提取值
     *
     * @param spEL   spEL表达式, 若为空则返回null
     * @param method 方法
     * @param args   参数
     * @return
     */
    public static <T> T parseSpEL(String spEL, Method method, Object[] args, Class<T> clazz) {
        if (StrUtil.isBlank(spEL)) {
            return null;
        }
        try {
            EvaluationContext context = new StandardEvaluationContext();
            String[] paramNames = NAME_DISCOVERER.getParameterNames(method);
            if (paramNames != null) {
                int len = Math.min(paramNames.length, args.length);
                for (int i = 0; i < len; i++) {
                    context.setVariable(paramNames[i], args[i]);
                }
            }
            Expression expression = CACHE.computeIfAbsent(spEL, PARSER::parseExpression);
            return expression.getValue(context, clazz);
        } catch (Exception e) {
            log.error("spEL表达式解析失败! spEL:{}, method:{}", spEL, method);
            throw e;
        }
    }
}
