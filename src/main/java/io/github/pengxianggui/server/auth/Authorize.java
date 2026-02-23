package io.github.pengxianggui.server.auth;

import java.lang.annotation.*;

/**
 * 自定义权限注解
 * <p>
 * 修饰在接口方法上，用于权限控制
 *
 * @author pengxg
 * @date 2026/2/20 22:01
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorize {
    /**
     * 拥有任一指定角色可放行 (例如: {"ADMIN", "USER"})。
     * <p>
     * 优先级高于{@link #value()}——即，当role指定了值，则指定的权限编码无效
     */
    String[] role() default {};

    /**
     * 拥有任一指定权限码可放行 (例如: {"sys:user:add"})
     */
    String[] value() default {};
}
