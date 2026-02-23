package io.github.pengxianggui.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author pengxg
 * @date 2026/2/19 14:22
 */
@ComponentScan(basePackages = {"io.github.pengxianggui.server", "com.gitee.sunchenbin.mybatis.actable.manager.*"})
@MapperScan({"io.github.pengxianggui.server.system.mapper", "com.gitee.sunchenbin.mybatis.actable.dao.*"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
