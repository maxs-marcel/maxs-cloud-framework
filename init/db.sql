/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : maxs

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 21/05/2024 15:08:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端标识',
  `resource_ids` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '接入资源列表',
  `client_secret` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) DEFAULT NULL COMMENT '访问范围标识',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '授权访问类型',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT 'web服务重定向uri',
  `authorities` varchar(255) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `archived` tinyint DEFAULT NULL,
  `trusted` tinyint DEFAULT NULL,
  `autoapprove` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='接入客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('maxs-manager', 'maxs-cloud-framework', '$2a$10$TuXsaX6tl3ys2/FKFLxYo.orUETQXymLvlwCzoJ5PmMlAkZ/9Q23G', 'all', 'password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 14400, 28800, NULL, '2024-05-14 07:29:51', 0, 0, 'false');
INSERT INTO `oauth_client_details` VALUES ('maxs-web', 'maxs-cloud-framework', '$2a$10$TuXsaX6tl3ys2/FKFLxYo.orUETQXymLvlwCzoJ5PmMlAkZ/9Q23G', 'all', 'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 3600, 7200, NULL, '2024-05-14 07:29:51', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父级权限id',
  `permission_code` varchar(128) DEFAULT NULL COMMENT '权限标识符',
  `access_path` varchar(512) DEFAULT NULL COMMENT '访问路径',
  `permission_type` tinyint DEFAULT NULL COMMENT '权限类型（1：目录；2：菜单；3：按钮）',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, '获取系统消息（测试）', -1, 'sys:test:getMsg', '/maxs-system/test/getMsg/**', 3, '获取系统消息（测试）');
INSERT INTO `sys_permission` VALUES (2, '系统管理', -1, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (3, '角色管理', 2, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (4, '角色绑定权限', 3, 'system:rolePermission:bind', '/maxs-system/role-permission/batchSaveUpdate', 3, NULL);
INSERT INTO `sys_permission` VALUES (5, '获取系统消息（feign）', -1, 'bus:test:sendMsg', '/maxs-business-one/test/sendMsg', 3, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(16) DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `role_state` tinyint DEFAULT NULL COMMENT '角色状态（0：禁用；1：启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', '所有权限', 1);
INSERT INTO `sys_role` VALUES (2, '系统级管理员', '只能访问System模块', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-角色-权限关系';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission` VALUES (21, 2, 2);
INSERT INTO `sys_role_permission` VALUES (22, 2, 3);
INSERT INTO `sys_role_permission` VALUES (23, 2, 4);
INSERT INTO `sys_role_permission` VALUES (24, 2, 5);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(512) DEFAULT NULL COMMENT '登录密码',
  `real_name` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `user_state` tinyint DEFAULT NULL COMMENT '用户状态（0：禁用；1：启用）',
  `del_flag` tinyint DEFAULT NULL COMMENT '删除标识',
  `create_user` bigint DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` bigint DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'zhangsan', '$2a$10$7Ci3lV9ThJ5eGM2qOsw2he93O0kjobljoLCRw0V.A.P9ftU0ZYY.i', '张三', '370802197904280328', '18692235711', 1, 0, 0, '2024-05-14 13:46:20', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'lisi', '$2a$10$7Ci3lV9ThJ5eGM2qOsw2he93O0kjobljoLCRw0V.A.P9ftU0ZYY.i', '李四', '522222199503063227', '18909489328', 1, 0, 0, '2024-05-14 13:47:03', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统-用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
