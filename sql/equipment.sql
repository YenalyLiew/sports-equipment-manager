/*
Navicat MySQL Data Transfer

Source Server         : localtest
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : book_manager

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2020-07-28 19:49:03
*/

SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------

-- ----------------------------
-- Records of book
-- ----------------------------
CREATE TABLE `equipment`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `equipment_id` varchar(255) DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `supplier`     varchar(255) DEFAULT NULL,
    `price`        double       DEFAULT NULL,
    `type`         varchar(255) DEFAULT NULL,
    `size`         int(11)      DEFAULT 0,
    `publish_time` datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 26
  DEFAULT CHARSET = utf8;

INSERT INTO `equipment` (`equipment_id`, `name`, `supplier`, `price`, `type`, `publish_time`, `size`)
VALUES ('E021', '篮球', '体育用品公司', 59.99, '体育器材', '2023-01-15 08:30:00.000000', 12),
       ('E022', '排球', '体育用品公司', 39.99, '体育器材', '2023-02-20 12:45:30.000000', 123),
       ('E023', '乒乓球', '运动设备厂', 9.99, '体育器材', '2023-03-25 15:10:45.123456', 12),
       ('E024', '弓', '射箭器材有限公司', 149.99, '射箭器材', '2023-04-10 08:30:00.000000', 11),
       ('E025', '箭', '射箭器材有限公司', 29.99, '射箭器材', '2023-05-05 05:55:55.555555', 1),
       ('E026', '羽毛球', '羽毛球用品专卖店', 4.99, '体育器材', '2023-06-18 18:12:34.567890', 5),
       ('E027', '羽毛球拍', '羽毛球用品专卖店', 29.99, '体育器材', '2023-07-22 10:15:30.987654', 4),
       ('E028', '瑜伽垫', '健康生活用品店', 19.99, '瑜伽器材', '2023-08-28 03:40:20.123456', 5);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `user_id`      int(11)     DEFAULT NULL,
    `equipment_id` int(11)     DEFAULT NULL,
    `create_time`  datetime(6) DEFAULT NULL,
    `end_time`     datetime(6) DEFAULT NULL,
    `update_time`  datetime(6) DEFAULT NULL,
    `ret`          int(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------

INSERT INTO `borrow` (`user_id`, `equipment_id`, `create_time`, `end_time`, `update_time`, `ret`)
VALUES (1, 21, '2023-01-15 10:00:00.000000', '2023-01-20 15:00:00.000000', '2023-01-19 12:30:00.000000', 0),
       (2, 22, '2023-02-05 14:30:00.000000', '2023-02-10 18:45:00.000000', '2023-02-09 09:15:00.000000', 0),
       (3, 23, '2023-03-10 09:45:00.000000', '2023-03-15 17:30:00.000000', '2023-03-14 14:00:00.000000', 1),
       (1, 24, '2023-04-20 08:00:00.000000', '2023-04-25 13:30:00.000000', '2023-04-24 10:00:00.000000', 0),
       (1, 25, '2023-05-01 12:30:00.000000', '2023-05-06 16:15:00.000000', '2023-05-05 08:45:00.000000', 1),
       (2, 26, '2023-06-15 17:00:00.000000', '2023-06-20 11:45:00.000000', '2023-06-19 10:15:00.000000', 0),
       (3, 27, '2023-07-02 08:15:00.000000', '2023-07-07 14:00:00.000000', '2023-07-06 12:30:00.000000', 0),
       (1, 28, '2023-08-10 14:00:00.000000', '2023-08-15 19:45:00.000000', '2023-08-14 16:15:00.000000', 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `address`  varchar(255) DEFAULT NULL,
    `avatar`   varchar(255) DEFAULT NULL,
    `birthday` datetime(6)  DEFAULT NULL,
    `email`    varchar(255) DEFAULT NULL,
    `identity` int(11)      DEFAULT NULL,
    `is_admin` int(11)      DEFAULT NULL,
    `nickname` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `size`     int(11)      DEFAULT NULL,
    `tel`      varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`
VALUES ('1', '上海', 'string', '2020-07-14 19:01:33.863000', 'stu@163.com', '0', '1', '学生', '123', '2', '13576145550',
        'stu');
INSERT INTO `users`
VALUES ('2', '上海', 'string', '2020-07-14 19:01:33.863000', 'tea@163.com', '1', '1', '教师', '123', '0', 'string',
        'tea');
INSERT INTO `users`
VALUES ('3', '北京', 'a.jpg', '2020-07-14 19:01:33.863000', 'other@163.com', '2', '1', '社会人士', '123', '3',
        '15078826452', 'other');
INSERT INTO `users`
VALUES ('4', '北京', 'b.jpa', '2020-07-16 16:06:43.000000', 'admin@163.com', '3', '0', '管理员', '123', '3',
        '17679088880', 'admin');
INSERT INTO `users`
VALUES ('8', '北京朝阳', 'b.jpa', '1998-02-12 08:00:00.000000', 'guest01@163.com', '3', '1', '游客1', '123', '10',
        '13576008880', 'guest01');
INSERT INTO `users`
VALUES ('9', '北京', 'b.jpa', '1982-01-01 08:00:00.000000', 'guest02@163.com', '3', '1', '游客2', '123', '10',
        '1768220000', 'guest02');
