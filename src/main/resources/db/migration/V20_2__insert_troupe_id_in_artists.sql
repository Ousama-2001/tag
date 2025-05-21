UPDATE `artists` SET `troupe_id` = NULL WHERE `troupe_id` IS NULL;
-- ou des valeurs de test, par exemple :
UPDATE `artists` SET `troupe_id` = 1 WHERE `id` = 1;
UPDATE `artists` SET `troupe_id` = 2 WHERE `id` = 2;
UPDATE `artists` SET `troupe_id` = 1 WHERE `id` = 3;
UPDATE `artists` SET `troupe_id` = 4 WHERE `id` = 4;
-- etc.

