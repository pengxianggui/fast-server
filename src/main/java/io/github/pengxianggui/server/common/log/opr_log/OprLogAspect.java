package io.github.pengxianggui.server.common.log.opr_log;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.pengxianggui.server.common.util.SpELUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author pengxg
 * @date 2026/2/22 14:08
 */
@Slf4j
@Aspect
@Component
public class OprLogAspect {

    @Autowired
    private OprLogUtil oprLogUtil;

    /**
     * 针对指定注解织入
     *
     * @param point
     * @param addOprLog
     * @return
     * @throws Throwable
     */
    @Around("@annotation(addOprLog)")
    public Object around(ProceedingJoinPoint point, AddOprLog addOprLog) throws Throwable {
        Object result = point.proceed();

        try {
            // 当前存在事务，注册事务提交后的回调
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        recordOprLog(point, addOprLog);
                    }
                });
            } else {
                recordOprLog(point, addOprLog);
            }
        } catch (Exception e) {
            log.error("操作日志记录异常!", e);
        }
        return result;
    }

    private void recordOprLog(ProceedingJoinPoint point, AddOprLog addOprLog) {
        String methodStr = null;
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            methodStr = method.toString();
            Object spELValue = SpELUtil.parseSpEL(addOprLog.value(), method, point.getArgs(), Object.class);
            if (spELValue == null) {
                log.warn("操作日志记录异常! spEL表达式({})解析结果为null! method:{}", addOprLog.value(), method.toGenericString());
                return;
            }

            String param = getParam(point);
            // 区分spELValue是集合、数组还是其它类型, 以便支持批量业务操作
            if (Collection.class.isAssignableFrom(spELValue.getClass())) { // 集合类型
                for (Object code : ((Collection<?>) spELValue)) {
                    oprLogUtil.recordBizLog(String.valueOf(code), addOprLog.desc(), param);
                }
            } else if (ArrayUtil.isArray(spELValue)) { // 数组类型
                int length = ArrayUtil.length(spELValue);
                for (int i = 0; i < length; i++) {
                    String code = ArrayUtil.get(spELValue, i);
                    oprLogUtil.recordBizLog(code, addOprLog.desc(), param);
                }
            } else { // 其它类型
                oprLogUtil.recordBizLog(String.valueOf(spELValue), addOprLog.desc(), param);
            }
        } catch (Exception e) {
            log.error(StrUtil.format("业务日志记录异常! method:", methodStr), e);
        }
    }

    /**
     * 获取参数的json/字符串 值
     *
     * @param point
     * @return
     */
    private String getParam(ProceedingJoinPoint point) {
        try {
            // 从point中解析出参数，如果是单个参数，则直接转为字符串(或json字符串),如果多个参数，则转为json字符串,key为参数名,value为参数值(字符串或json字符串)
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            Object[] args = point.getArgs();

            if (args == null || args.length == 0) {
                return null;
            }
            String[] paramNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
            if (args.length == 1) {
                // 单个参数，直接转换为JSON字符串
                return JSONUtil.toJsonStr(args[0]);
            } else {
                // 多个参数，构建参数名-参数值映射
                Map<String, Object> paramMap = new HashMap<>();
                if (paramNames != null) {
                    for (int i = 0; i < args.length && i < paramNames.length; i++) {
                        paramMap.put(paramNames[i], args[i]);
                    }
                } else {
                    // 如果无法获取参数名，则使用索引作为键
                    for (int i = 0; i < args.length; i++) {
                        paramMap.put("arg" + i, args[i]);
                    }
                }
                return JSONUtil.toJsonStr(paramMap);
            }
        } catch (Exception e) {
            log.error("参数解析异常!", e);
            return null;
        }
    }

}
