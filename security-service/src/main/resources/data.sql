
-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(100) NOT NULL,
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `menu_component` varchar(100) DEFAULT NULL COMMENT '菜单组件',
  `menu_router` varchar(100) DEFAULT NULL COMMENT '菜单路径',
  `status` varchar(2) NOT NULL COMMENT '状态; 1:启用;2:禁用',
  `parent_id` varchar(100) DEFAULT NULL COMMENT '父级菜单ID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', 'fa fa-windows', '/', 'Home', '/home', '1', null, '2019-11-27 10:23:28', '2019-11-27 10:23:28');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', null, '/system/user/**', 'system/User', '/sys/user', '1', '1', '2019-11-27 10:23:28', '2019-11-27 10:23:28');
INSERT INTO `sys_menu` VALUES ('3', '测试', null, '/system/test/**', 'system/Hello', '/sys/hello', '1', '1', '2019-11-27 10:23:28', '2019-11-27 10:23:28');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(100) NOT NULL,
  `role_name` varchar(100) NOT NULL COMMENT '角色',
  `role_alias` varchar(100) NOT NULL COMMENT '别名',
  `status` varchar(2) NOT NULL COMMENT '状态; 1:启用;2:禁用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '管理员', '1', '2019-09-09 10:14:04', '2019-09-09 10:14:06');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '普通用户', '1', '2019-09-09 10:14:04', '2019-09-09 10:14:06');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(100) NOT NULL,
  `role_id` varchar(100) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(100) DEFAULT NULL COMMENT '菜单ID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '2', NOW(), NOW());
INSERT INTO `sys_role_menu` VALUES ('2', '1', '3', NOW(), NOW());

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL COMMENT '姓名',
  `user_phone` varchar(100) NOT NULL COMMENT '手机号码',
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `status` varchar(2) NOT NULL COMMENT '状态; 1:启用;2:禁用',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('admin', 'admin', '13771234567', '系统管理员', '$2a$10$v27SeU4hUCYOoBVYjdlOIuH9x5BQiJTKPJ6zjYqVFGHzVtjR3rjLi', '1', NOW(), NOW());

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) NOT NULL COMMENT '用户ID',
  `role_id` varchar(100) NOT NULL COMMENT '角色ID',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'admin', '1', NOW(), NOW());
