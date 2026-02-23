package io.github.pengxianggui.server.common.log.opr_log;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author pengxg
 * @date 2026/2/22 14:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddOprLog {
    /**
     * 业务数据唯一标识值的SpEL表达式。例如：
     *
     * <pre>
     * // eg1
     * &#064;AddBizLog(value  = "#orderNo", ..)
     * public void methodName(String orderNo) {
     *     // ...
     * }
     *
     * // eg2
     * &#064;AddBizLog(value  = "#order.orderNo", ..)
     * public void methodName(Order order) {
     *     // ...
     * }
     *
     * 如果methodName方法是对单据执行批量业务操作(参数格式并不那么"友好")，
     * 如果想同时针对批量操作的单据记录业务日志，spEL怎么写呢，查看eg3和eg4：
     * // eg3
     * &#064;AddBizLog(value  = "#orderNos", ..)
     * public void methodName(List&lt;String> orderNos) {
     *     // ...
     * }
     *
     * // eg4
     * &#064;AddBizLog(value  = "#request.items?.![orderNo]", ..)
     * public void methodName(XXRequest request) {
     *     for(XXRequestItem item : request.getItems()) {
     *         String orderNo = item.getOrderNo();
     *         // ....
     *     }
     * }
     * </pre>
     *
     * @return 注意: 推荐用唯一编码值
     */
    String value() default "";

    /**
     * 操作日志的简述
     *
     * @return
     */
    String desc() default "";
}
