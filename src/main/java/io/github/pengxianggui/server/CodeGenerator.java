package io.github.pengxianggui.server;

import io.github.pengxianggui.crud.autogenerate.CodeAutoGenerator;
import io.github.pengxianggui.server.system.model.entity.BaseEntity;

/**
 * 基于fast-crud的代码生成器，可快速生成满足fast-crud规范的代码(controller、service、mapper、entity)
 *
 * @author pengxg
 * @date 2026/2/19 14:25
 */
public class CodeGenerator {

    public static void main(String[] args) {
        CodeAutoGenerator.builder()
                .author("pengxg")
//                .module("server") // 模块名: maven多模块时
                .url("jdbc:mysql://127.0.0.1:3306/fast-server")
//                .schema("fast-crud")
                .username("root")
                .password("123456")
                .parentPkg("io.github.pengxianggui.server.system")
                .entitySuperClass(BaseEntity.class)
                .build()
                .generate();

    }
}
