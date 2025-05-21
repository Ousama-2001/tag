CREATE TABLE `troupes` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(60) NOT NULL,
                           `logo_url` VARCHAR(255) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
  COMMENT='Liste des troupes';