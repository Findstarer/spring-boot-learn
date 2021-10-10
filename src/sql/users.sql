CREATE SCHEMA `beta` DEFAULT CHARACTER SET utf8mb4 ;
use beta;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name` varchar(32) DEFAULT NULL COMMENT '用户名',
    `hometown` varchar(32) DEFAULT NULL COMMENT '家乡',
    `sex` varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `users` VALUES (1,'michael翔', '北京', 'MAN');
INSERT INTO `users` VALUES (2,'张小敬', '武汉', 'MAN');
INSERT INTO `users` VALUES (3,'李司辰', '湖北', 'MAN');
INSERT INTO `users` VALUES (4,'崔器', '南京', 'MAN');
INSERT INTO `users` VALUES (5,'姚汝能', '湖南', 'MAN');
INSERT INTO `users` VALUES (null,'檀棋', '河南', ' WOMAN');

