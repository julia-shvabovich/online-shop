CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `id`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `price`   DOUBLE       NOT NULL,
    `deleted` TINYINT      NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `internet_shop`.`roles`
(
    `id`   BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `internet_shop`.`users`
(
    `id`       BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `login`    VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `salt`     VARBINARY(16) NOT NULL,
    `deleted`  TINYINT GENERATED ALWAYS AS (0) VIRTUAL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `internet_shop`.`users_roles`
(
    `user_id` BIGINT(11) NOT NULL,
    `role_id` BIGINT(11) NOT NULL,
    INDEX `ur_role_id_fk_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `ur_role_id_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `internet_shop`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `ur_user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`orders`
(
    `id`      BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT GENERATED ALWAYS AS (0) VIRTUAL,
    CONSTRAINT `o_user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    PRIMARY KEY (`id`)
);

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `id`      BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT GENERATED ALWAYS AS (0) VIRTUAL,
    CONSTRAINT `sc_user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    PRIMARY KEY (`id`)
);

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `cart_id`    BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
    INDEX `scu_product_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `scu_cart_id_fk`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `scu_product_id_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`orders_products`
(
    `order_id`   BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
    INDEX `op_product_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `op_order_id_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internet_shop`.`orders` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `op_product_id_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO `roles`(name)
VALUES ('ADMIN');
INSERT INTO `roles`(name)
VALUES ('USER');
INSERT INTO `users`(name, login, password)
VALUES ('admin', 'admin', '12345');
INSERT INTO `users_roles`(user_id, role_id)
VALUES (1, 2);
INSERT INTO 'products' (name, price)
VALUES ('test', 100);
