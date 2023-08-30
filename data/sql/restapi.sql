UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `restapi`.`user` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `restapi`.`user`(`name`, `email`) VALUES ('Example', 'example@gmail.com');

UNLOCK TABLES;
