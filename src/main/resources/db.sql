DROP DATABASE IF EXISTS `db_rbac`;
CREATE DATABASE IF NOT EXISTS `db_rbac`
  CHARACTER SET UTF8;

USE `db_rbac`;

CREATE TABLE IF NOT EXISTS `t_user` (
  `id`       CHAR(32)    NOT NULL
  COMMENT '用户ID',
  `email`    VARCHAR(50) NOT NULL
  COMMENT '用户邮箱',
  `nickname` VARCHAR(50) NOT NULL
  COMMENT '用户昵称',
  `password` VARCHAR(32) NOT NULL
  COMMENT '用户密码',
  `update`   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `t_role` (
  `id`          INT(11)       NOT NULL AUTO_INCREMENT
  COMMENT '角色ID',
  `role_name`   VARCHAR(50)   NOT NULL
  COMMENT '角色名称',
  `description` VARCHAR(1000) NOT NULL
  COMMENT '角色描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 50
  DEFAULT CHARSET = utf8
  COMMENT '角色表';

CREATE TABLE IF NOT EXISTS `t_privilege` (
  `id`             INT(11)       NOT NULL AUTO_INCREMENT
  COMMENT '权限ID',
  `privilege_name` VARCHAR(50)   NOT NULL
  COMMENT '权限名称',
  `privilege_url`  VARCHAR(500)  NOT NULL
  COMMENT '权限URL',
  `description`    VARCHAR(3000) NOT NULL
  COMMENT '权限描述',
  `no_login`       CHAR(1)       NOT NULL
  COMMENT '无需登录访问',
  PRIMARY KEY (`id`),
  UNIQUE KEY `privilege_name` (`privilege_name`),
  UNIQUE KEY `privilege_url` (`privilege_url`, `no_login`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 50
  DEFAULT CHARSET = utf8
  COMMENT '权限表';

CREATE TABLE IF NOT EXISTS `t_user_role` (
  `user_id` CHAR(32) NOT NULL,
  `role_id` INT(11)  NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  KEY (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `t_role_privilege` (
  `role_id`      INT(11) NOT NULL,
  `privilege_id` INT(11) NOT NULL,
  PRIMARY KEY (`role_id`, `privilege_id`),
  KEY (`privilege_id`),
  CONSTRAINT `role_privilege_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `role_privilege_ibfk_2` FOREIGN KEY (`privilege_id`) REFERENCES `t_privilege` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

LOCK TABLES `t_user` WRITE;
INSERT INTO `t_user`
VALUES
  ('760bfab9eb023d64ab2dbb23eaa0bf9e', 'admin@gmail.com', 'admin', '8e3d3cec19312c5866a93f6bae11b262',
   CURRENT_TIMESTAMP),
  ('56972e2680ac31a688ddaead934ed9ec', 'user_admin@gmail.com', 'user_admin', '8e3d3cec19312c5866a93f6bae11b262',
   CURRENT_TIMESTAMP);
UNLOCK TABLES;

LOCK TABLES `t_role` WRITE;
INSERT INTO `t_role` VALUES
  (21, 'admin', '超级管理员, 拥有最高权限'),
  (22, 'user_admin', '用户管理, 拥有最基本的权限和管理用户的权限'),
  (31, 'user', '普通用户, 拥有最基本的权限');
UNLOCK TABLES;

LOCK TABLES `t_privilege` WRITE;
INSERT INTO `t_privilege`
VALUES
  (1, '用户管理', '/user/manage', '新建, 修改和删除用户的功能', '0'),
  (2, '角色管理', '/role/manage', '新建, 修改和删除库尔色的功能', '0'),
  (3, '权限管理', '/privilege/manage', '新建, 修改和删除权限的功能', '0'),
  (4, '用户基本操作', '/user/operation', '登录, 注册, 登出的功能', '1'),
  (5, '主页', '/', '访问主页', '1'),
  (6, 'index', '/index.jsp', '访问index.jsp页面', '1'),
  (7, 'login', '/pages/user/operation/login.jsp', '访问用户登录页面', '1'),
  (8, 'register', '/pages/user/operation/register.jsp', '访问用户注册页面', '1'),
  (9, '验证码请求', '/verify_code', '请求验证码', '1');
UNLOCK TABLES;

LOCK TABLES `t_user_role` WRITE;
INSERT INTO `t_user_role` VALUES
  ('760bfab9eb023d64ab2dbb23eaa0bf9e', 21);
UNLOCK TABLES;

LOCK TABLES `t_role_privilege` WRITE;
# 设置超级管理员的色权限
INSERT INTO `t_role_privilege`
VALUES
  (21, 1), (21, 2), (21, 3), (21, 4);
UNLOCK TABLES;
