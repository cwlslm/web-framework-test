CREATE DATABASE IF NOT EXISTS `web_framework_chapter2_test` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
USE `web_framework_chapter2_test`;

CREATE TABLE IF NOT EXISTS `customer` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `contact` varchar(255) DEFAULT NULL,
    `telephone` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `photo` varchar(255) DEFAULT NULL,
    `remark` text,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;