CREATE TABLE `artist_type`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `artist_id` int(11) NOT NULL,
    `type_id`   int(11) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

--
-- Index pour la table `artist_type`
--
ALTER TABLE `artist_type`
    ADD KEY `artist_type_artist_id_ IDX_3060D1B6B7970CF8_fk_artists_id` (`artist_id`);

ALTER TABLE `artist_type`
    ADD KEY `artist_type_type_id_ IDX_3060D1B6C54C8C93_fk_types_id` (`type_id`);

--
-- Contraintes pour la table `artist_type`
--
ALTER TABLE `artist_type`
    ADD CONSTRAINT `artist_type_artist_id_ 3060D1B6B7970CF8_fk_artists_id` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `artist_type`
    ADD CONSTRAINT `artist_type_type_id_ 3060D1B6C54C8C93 _fk_artists_id` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;
INSERT INTO `artist_type` (`id`, `artist_id`, `type_id`)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 1, 2),
       (4, 2, 2),
       (5, 1, 3),
       (6, 3, 1),
       (7, 4, 2),
       (8, 5, 3),
       (9, 6, 3),
       (10, 7, 3),
       (11, 8, 3),
       (12, 9, 3),
       (13, 10, 1),
       (14, 11, 2),
       (15, 10, 3),
       (16, 12, 1),
       (17, 13, 1),
       (18, 12, 3),
       (19, 13, 3);
