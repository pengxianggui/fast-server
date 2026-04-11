package io.github.pengxianggui.server.auth;

import io.github.pengxianggui.server.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 配置类
 *
 * @author pengxg
 * @date 2026/2/20 22:48
 */
@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityExceptionHandler securityExceptionHandler;
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/captcha", "/error", "/pub/**").permitAll() // 登录、验证码接口开放, /pub/前缀的开放
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(securityExceptionHandler)
                .accessDeniedHandler(securityExceptionHandler);
        ;

        http.addFilterBefore(new JwtAuthFilter(userService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 这里的资源将完全绕过 Spring Security，不经过任何过滤器（包括你的 JwtAuthFilter）
        return (web) -> web.ignoring().antMatchers(
                "/favicon.ico",
                "/error",
                "/webjars/**",
                "/doc.html", // 如果你用了 swagger/knife4j
                "/swagger-resources/**",
                "/v2/api-docs/**"
        );
    }
}
