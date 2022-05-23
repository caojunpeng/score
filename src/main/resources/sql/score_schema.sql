/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : score

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 23/05/2022 09:33:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` int(10) NOT NULL COMMENT '字典ID（当type=2时，班级）',
  `type_id` int(10) NOT NULL COMMENT '字典类型',
  `dict_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容信息',
  `dict_msg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述（当type=2时，年级）',
  `dict_status` int(2) DEFAULT 0 COMMENT '状态：0-可用；1-禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (1, 1, 1, '语文', NULL, 0);
INSERT INTO `dict` VALUES (2, 2, 1, '数学', NULL, 0);
INSERT INTO `dict` VALUES (3, 3, 1, '英语', NULL, 0);
INSERT INTO `dict` VALUES (4, 4, 1, '政治', NULL, 0);
INSERT INTO `dict` VALUES (5, 5, 1, '历史', NULL, 0);
INSERT INTO `dict` VALUES (6, 6, 1, '地理', NULL, 0);
INSERT INTO `dict` VALUES (7, 7, 1, '生物', NULL, 0);
INSERT INTO `dict` VALUES (8, 8, 1, '物理', NULL, 0);
INSERT INTO `dict` VALUES (9, 9, 1, '化学', NULL, 0);
INSERT INTO `dict` VALUES (10, 10, 2, '1', '6', 0);
INSERT INTO `dict` VALUES (11, 11, 2, '2', '6', 0);
INSERT INTO `dict` VALUES (12, 12, 2, '3', '6', 0);
INSERT INTO `dict` VALUES (13, 13, 2, '11', '7', 0);
INSERT INTO `dict` VALUES (14, 14, 2, '21', '7', 0);
INSERT INTO `dict` VALUES (15, 15, 2, '31', '7', 0);
INSERT INTO `dict` VALUES (16, 16, 2, '12', '8', 0);
INSERT INTO `dict` VALUES (17, 17, 2, '22', '8', 0);
INSERT INTO `dict` VALUES (18, 18, 2, '32', '8', 0);
INSERT INTO `dict` VALUES (19, 19, 2, '13', '9', 0);
INSERT INTO `dict` VALUES (20, 20, 2, '23', '9', 0);
INSERT INTO `dict` VALUES (21, 21, 2, '33', '9', 0);

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type_id` int(10) NOT NULL COMMENT '字典类型',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, 1, '科目类型');
INSERT INTO `dict_type` VALUES (2, 2, '年级、班级');

-- ----------------------------
-- Table structure for grade_class
-- ----------------------------
DROP TABLE IF EXISTS `grade_class`;
CREATE TABLE `grade_class`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_num` int(10) DEFAULT NULL COMMENT '班级',
  `grade_num` int(10) DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '年级班级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade_class
-- ----------------------------
INSERT INTO `grade_class` VALUES (1, 1, 6);
INSERT INTO `grade_class` VALUES (2, 2, 6);
INSERT INTO `grade_class` VALUES (3, 3, 6);
INSERT INTO `grade_class` VALUES (4, 1, 7);
INSERT INTO `grade_class` VALUES (5, 2, 7);
INSERT INTO `grade_class` VALUES (6, 3, 7);
INSERT INTO `grade_class` VALUES (7, 1, 8);
INSERT INTO `grade_class` VALUES (8, 2, 8);
INSERT INTO `grade_class` VALUES (9, 3, 8);
INSERT INTO `grade_class` VALUES (10, 1, 9);
INSERT INTO `grade_class` VALUES (11, 2, 9);
INSERT INTO `grade_class` VALUES (12, 3, 9);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签',
  `parent_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级名称',
  `action` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求地址',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态:1可用;0:禁用',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `menu_function` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '点击菜单触发函数',
  `menu_style` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单样式名',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'main', '系统管理', 'root', NULL, NULL, 1, NULL, '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (2, 'studentInfoEnter', '学生录入', 'main', '/students/studentInfoEnter', 1, 1, 'glyphicon glyphicon-open-file', '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (3, 'studentInfoManagement', '学生管理', 'main', '/students/studentInfoManagement', 2, 1, 'glyphicon glyphicon-tasks', '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (4, 'scoreEnter', '成绩录入', 'main', '/scores/scoreEnter', 3, 1, 'glyphicon glyphicon-copy', '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (5, 'scoreManagement', '成绩管理', 'main', '/scoreNumber/scoreNumberManagement', 4, 1, 'glyphicon glyphicon-list-alt', '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (6, 'admin', '系统管理', 'root', NULL, NULL, 1, NULL, '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (7, 'userManagement', '用户管理', 'admin', '/user/main', 1, 1, NULL, '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (8, 'roleManagement', '角色管理', 'admin', '/role/main', 2, 1, NULL, '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (9, 'menuManagement', '菜单管理', 'admin', '/menu/main', 3, 1, NULL, '2022-04-10 10:19:34', 'superadmin', NULL, NULL);
INSERT INTO `menu` VALUES (10, 'teacherManagement', '教师管理', 'main', '/teachers/teacherInfoManagement', 6, 1, 'glyphicon glyphicon-align-left', '2022-04-10 10:19:34', 'superadmin', NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_type` int(2) DEFAULT NULL COMMENT '角色类型：0-管理员；1-教师；2-学生',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 0, '2022-04-05 17:55:36', '管理员', NULL);
INSERT INTO `role` VALUES (2, '教师', 1, '2022-04-24 10:18:03', '教师', NULL);
INSERT INTO `role` VALUES (3, '学生', 2, '2022-04-12 17:55:40', '学生', NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (21, 2, 1);
INSERT INTO `role_menu` VALUES (22, 2, 2);
INSERT INTO `role_menu` VALUES (23, 2, 3);
INSERT INTO `role_menu` VALUES (24, 2, 4);
INSERT INTO `role_menu` VALUES (25, 2, 5);
INSERT INTO `role_menu` VALUES (26, 2, 10);
INSERT INTO `role_menu` VALUES (27, 3, 1);
INSERT INTO `role_menu` VALUES (28, 3, 3);
INSERT INTO `role_menu` VALUES (29, 3, 5);
INSERT INTO `role_menu` VALUES (30, 1, 1);
INSERT INTO `role_menu` VALUES (31, 1, 2);
INSERT INTO `role_menu` VALUES (32, 1, 3);
INSERT INTO `role_menu` VALUES (33, 1, 4);
INSERT INTO `role_menu` VALUES (34, 1, 5);
INSERT INTO `role_menu` VALUES (35, 1, 10);
INSERT INTO `role_menu` VALUES (36, 1, 6);
INSERT INTO `role_menu` VALUES (37, 1, 7);
INSERT INTO `role_menu` VALUES (38, 1, 8);
INSERT INTO `role_menu` VALUES (39, 1, 9);

-- ----------------------------
-- Table structure for score_number
-- ----------------------------
DROP TABLE IF EXISTS `score_number`;
CREATE TABLE `score_number`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `score_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考试编号',
  `score_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场次名称',
  `score_time` datetime(0) DEFAULT NULL COMMENT '考试日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '考试场次' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for score_ranking
-- ----------------------------
DROP TABLE IF EXISTS `score_ranking`;
CREATE TABLE `score_ranking`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学生编号',
  `score_sum` double DEFAULT NULL COMMENT '学生总分',
  `grade_ranking` int(10) DEFAULT NULL COMMENT '年级排名',
  `class_ranking` int(10) DEFAULT NULL COMMENT '班级排名',
  `score_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考试场次',
  `class_num` int(10) DEFAULT NULL COMMENT '班级',
  `grade_num` int(10) DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 153 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '成绩排名分析表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `student_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
  `score` double NOT NULL COMMENT '分数',
  `subject` int(10) NOT NULL COMMENT '科目',
  `qualified_state` int(2) DEFAULT NULL COMMENT '合格状态 0:不及格  1:及格',
  `score_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '考试编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 460 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `student_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女；',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `birthdate` datetime(0) DEFAULT NULL COMMENT '出生日期',
  `identity_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '家庭住址',
  `grade_num` int(10) NOT NULL COMMENT '年级',
  `class_num` int(10) NOT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别：1-男；2-女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `identity_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `subject` int(10) DEFAULT NULL COMMENT '任职科目：见字典type_id=2',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `grade_num` int(10) NOT NULL COMMENT '年级',
  `class_num` int(10) NOT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teaching
-- ----------------------------
DROP TABLE IF EXISTS `teaching`;
CREATE TABLE `teaching`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classId` int(10) DEFAULT NULL COMMENT '课程ID',
  `teacherId` int(10) DEFAULT NULL COMMENT '教师ID',
  `grade_num` int(10) DEFAULT NULL COMMENT '年纪ID',
  `class_num` int(10) DEFAULT NULL COMMENT '班级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '教师职务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_pwd` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `status` int(2) DEFAULT NULL COMMENT '状态:0 禁用;1 使用',
  `original_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '默认密码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'd5f5a301c41aad2fa2b93316a76b3159', 'EJm8CKtp', '15985662354', '1162408885@qq.com', '管理员', '2022-04-13 17:53:06', NULL, 1, '123456');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (13, 3, 16);
INSERT INTO `user_role` VALUES (14, 1, 1);
INSERT INTO `user_role` VALUES (15, 3, 17);
INSERT INTO `user_role` VALUES (16, 3, 19);
INSERT INTO `user_role` VALUES (19, 2, 20);
INSERT INTO `user_role` VALUES (20, 3, 21);
INSERT INTO `user_role` VALUES (21, 3, 22);
INSERT INTO `user_role` VALUES (22, 3, 23);
INSERT INTO `user_role` VALUES (23, 3, 24);
INSERT INTO `user_role` VALUES (24, 3, 25);
INSERT INTO `user_role` VALUES (25, 3, 26);
INSERT INTO `user_role` VALUES (26, 3, 27);
INSERT INTO `user_role` VALUES (27, 3, 28);
INSERT INTO `user_role` VALUES (28, 3, 29);
INSERT INTO `user_role` VALUES (29, 3, 30);
INSERT INTO `user_role` VALUES (30, 3, 31);
INSERT INTO `user_role` VALUES (31, 3, 32);
INSERT INTO `user_role` VALUES (33, 3, 34);
INSERT INTO `user_role` VALUES (34, 3, 35);
INSERT INTO `user_role` VALUES (35, 3, 36);
INSERT INTO `user_role` VALUES (36, 3, 37);
INSERT INTO `user_role` VALUES (37, 3, 38);
INSERT INTO `user_role` VALUES (38, 3, 39);
INSERT INTO `user_role` VALUES (39, 3, 40);
INSERT INTO `user_role` VALUES (42, 3, 33);
INSERT INTO `user_role` VALUES (43, 2, 41);
INSERT INTO `user_role` VALUES (44, 2, 42);
INSERT INTO `user_role` VALUES (45, 3, 43);
INSERT INTO `user_role` VALUES (46, 3, 44);
INSERT INTO `user_role` VALUES (48, 3, 46);
INSERT INTO `user_role` VALUES (49, 3, 47);
INSERT INTO `user_role` VALUES (50, 3, 48);
INSERT INTO `user_role` VALUES (51, 3, 49);
INSERT INTO `user_role` VALUES (52, 3, 50);
INSERT INTO `user_role` VALUES (53, 3, 51);
INSERT INTO `user_role` VALUES (54, 3, 52);
INSERT INTO `user_role` VALUES (55, 3, 53);
INSERT INTO `user_role` VALUES (56, 3, 54);
INSERT INTO `user_role` VALUES (57, 2, 55);
INSERT INTO `user_role` VALUES (58, 1, 45);

SET FOREIGN_KEY_CHECKS = 1;
