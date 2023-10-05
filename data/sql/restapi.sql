UNLOCK TABLES;

DROP TABLE IF EXISTS `company`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `confirm_token`;

CREATE TABLE `restapi`.`company`
(
    `id`         bigint(19) unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)        NOT NULL,
    `created_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE `restapi`.`user`
(
    `id`         bigint(19) unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)        NOT NULL,
    `email`      varchar(255)        NOT NULL,
    `company_id` bigint(19)          NOT NULL,
    `created_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE `restapi`.`confirm_token`
(
    `id`         bigint(19) unsigned NOT NULL AUTO_INCREMENT,
    `token`      varchar(255)        NOT NULL,
    `user_id`    bigint(19)          NOT NULL,
    `created_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET = utf8;

INSERT INTO `restapi`.`company`(`id`, `name`)
VALUES (1, 'Sun-Asterisk');

INSERT INTO `restapi`.`user`(`id`, `name`, `email`, `company_id`)
VALUES (1, 'Example 1', 'example1@gmail.com', 1);

INSERT INTO `restapi`.`user`(`id`, `name`, `email`, `company_id`)
VALUES (2, 'Example 2', 'example2@gmail.com', 1);

INSERT INTO `restapi`.`confirm_token`(`token`, `user_id`)
VALUES ('TOKEN-1', 1);

INSERT INTO `restapi`.`confirm_token`(`token`, `user_id`)
VALUES ('TOKEN-2', 2);
UNLOCK TABLES;
