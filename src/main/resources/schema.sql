/*
 Navicat Premium Dump SQL

 Source Server         : localhost-mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50724 (5.7.24)
 Source Host           : localhost:3306
 Source Schema         : fast-server

 Target Server Type    : MySQL
 Target Server Version : 50724 (5.7.24)
 File Encoding         : 65001

 Date: 04/03/2026 17:31:21
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth
-- ----------------------------
CREATE TABLE IF NOT EXISTS `auth`
(
    `code`        varchar(50) NOT NULL COMMENT '权限编码',
    `name`        varchar(50) NOT NULL COMMENT '权限名称',
    `description` varchar(300) DEFAULT NULL COMMENT '权限描述',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20)  DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    `module_id`   bigint(20) DEFAULT NULL COMMENT '模块ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `actable_uni_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Table structure for module
-- ----------------------------
CREATE TABLE IF NOT EXISTS `module`
(
    `name`        varchar(50) NOT NULL COMMENT '模块名称',
    `description` varchar(300) DEFAULT NULL COMMENT '模块描述',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20)  DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    `parent_id`   bigint(20) DEFAULT NULL COMMENT '父模块ID(为null则表示为根模块)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='模块(权限分组)';

-- ----------------------------
-- Table structure for opr_log
-- ----------------------------
CREATE TABLE IF NOT EXISTS `opr_log`
(
    `code`          varchar(200) DEFAULT NULL COMMENT '业务单据编码值',
    `operate_desc`  varchar(200) DEFAULT NULL COMMENT '操作描述',
    `operate_time`  datetime     DEFAULT NULL COMMENT '操作时间',
    `ip`            varchar(20)  DEFAULT NULL COMMENT '操作来源IP',
    `terminal_type` varchar(20)  DEFAULT NULL COMMENT '操作终端类型',
    `param`         longtext COMMENT '操作参数',
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`     bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name`   varchar(20)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time`   datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`     bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name`   varchar(20)  DEFAULT NULL COMMENT '更新人姓名',
    `update_time`   datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`       tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志';

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `role`
(
    `code`        varchar(50) NOT NULL COMMENT '角色编码',
    `name`        varchar(50) NOT NULL COMMENT '角色名称',
    `description` varchar(300) DEFAULT NULL COMMENT '角色描述',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20)  DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `actable_uni_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Table structure for role_auth_rel
-- ----------------------------
CREATE TABLE IF NOT EXISTS `role_auth_rel`
(
    `role_id`     bigint(20) NOT NULL COMMENT '角色ID',
    `auth_id`     bigint(20) NOT NULL COMMENT '权限ID',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20) DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user`
(
    `username`    varchar(50) NOT NULL COMMENT '用户名',
    `password`    varchar(200) DEFAULT NULL COMMENT '密码',
    `phone`       varchar(20)  DEFAULT NULL COMMENT '手机号',
    `nick_name`   varchar(50)  DEFAULT NULL COMMENT '昵称',
    `real_name`   varchar(50)  DEFAULT NULL COMMENT '真实姓名',
    `id_card`     varchar(20)  DEFAULT NULL COMMENT '身份证号',
    `email`       varchar(100) DEFAULT NULL COMMENT '邮箱',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20)  DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20)  DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `actable_uni_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_role_rel`
(
    `user_id`     bigint(20) NOT NULL COMMENT '用户ID',
    `role_id`     bigint(20) NOT NULL COMMENT '角色ID',
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `create_by`   bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_name` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_name` varchar(20) DEFAULT NULL COMMENT '更新人姓名',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否;1-是)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

SET
FOREIGN_KEY_CHECKS = 1;
