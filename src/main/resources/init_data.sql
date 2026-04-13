-- ----------------------------
-- Records of auth
-- ----------------------------
BEGIN;
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:user:delete', '删除系统用户', NULL, 1, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 3);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:user:edit', '编辑系统用户', NULL, 2, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 3);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:user:add', '创建系统用户', NULL, 3, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 3);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:role:delete', '删除系统角色', NULL, 4, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 4);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:role:edit', '修改系统角色', NULL, 5, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 4);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:role:add', '创建系统角色', NULL, 6, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 4);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:user:query', '查询系统用户', NULL, 7, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 3);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:role:query', '查询系统角色', NULL, 8, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 4);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:auth:delete', '删除系统权限', NULL, 9, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 5);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:auth:edit', '修改系统权限', NULL, 10, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 5);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:auth:add', '创建系统权限', NULL, 11, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 5);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:auth:query', '查询系统权限', NULL, 12, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 5);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:user:bind-role', '给用户指定角色', NULL, 14, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 3);
INSERT INTO `auth` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`, `module_id`)
VALUES ('sys:role:bind-auth', '给角色绑定权限', NULL, 15, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 4);
COMMIT;


-- ----------------------------
-- Records of module
-- ----------------------------
BEGIN;
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('系统', NULL, 1, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, NULL);
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('系统管理', '', 2, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 1);
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('用户管理', NULL, 3, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 2);
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('角色管理', NULL, 4, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 2);
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('权限管理', NULL, 5, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 2);
INSERT INTO `module` (`name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                      `update_name`, `update_time`, `deleted`, `parent_id`)
VALUES ('字典管理', '', 6, 1, '管理员', NOW(), 1, '管理员', NOW(), 0, 2);
COMMIT;


-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`code`, `name`, `description`, `id`, `create_by`, `create_name`, `create_time`, `update_by`,
                    `update_name`, `update_time`, `deleted`)
VALUES ('admin', '管理员', '拥有系统所有权限，也是系统内置角色。勿删除！', 1, 1, '管理员', NOW(), 1, '管理员', NOW(), 0);
COMMIT;


-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`username`, `password`, `phone`, `nick_name`, `real_name`, `id_card`, `email`, `id`, `create_by`,
                    `create_name`, `create_time`, `update_by`, `update_name`, `update_time`, `deleted`)
VALUES ('admin', '$2a$10$qlGjF1aazBNI8s/SIkY7i.z5elQ9YHX088.kvGTPSlicq1Eogxp/G', '', '管理员', '管理员', NULL, NULL, 1,
        1, '管理员', NOW(), 1, '管理员', NOW(), 0);
COMMIT;

BEGIN;
INSERT INTO `user_role_rel` (`user_id`, `role_id`, `create_by`, `create_name`, `create_time`, `update_by`, `update_name`, `update_time`, `deleted`) VALUES (1, 1, 1, 'admin', NOW(), 1, 'admin', NOW(), 0);
COMMIT;