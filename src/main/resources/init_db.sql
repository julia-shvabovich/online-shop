CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `id`         BIGINT          NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)    NOT NULL,
    `price`      DOUBLE          NOT NULL,
    `deleted`    TINYINT         NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);
