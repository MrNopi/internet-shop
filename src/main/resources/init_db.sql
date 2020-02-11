CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `shop`.`items` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),

  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `shop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE);

CREATE TABLE `shop`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `token` VARCHAR(255) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);

CREATE TABLE `shop`.`orders_items` (
  `order_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`order_items_id`),
  UNIQUE INDEX `order_items_id_UNIQUE` (`order_items_id` ASC) VISIBLE,
  INDEX `order_items_orders_fk_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `order_items_orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `shop`.`orders_items` 
ADD INDEX `order_items_items_fk_idx`
 (`item_id` ASC) VISIBLE;
;
ALTER TABLE `shop`.`orders_items` 
ADD CONSTRAINT `order_items_items_fk`
 
 FOREIGN KEY (`item_id`)
  REFERENCES `shop`.`items` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `shop`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));

CREATE TABLE `shop`.`users_roles` (
  `users_roles_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`users_roles_id`),
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
INSERT INTO `shop`.`roles` (`role`) VALUES ('USER');
INSERT INTO `shop`.`roles` (`role`) VALUES ('ADMIN');

INSERT INTO `shop`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `shop`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');

CREATE TABLE `shop`.`buckets` (
  `bucket_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`bucket_id`),
  UNIQUE INDEX `buket_id_UNIQUE` (`buket_id` ASC) VISIBLE,
  INDEX `bucket_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `bucket_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `shop`.`bucket_items` (
  `bucket_items_id` INT NOT NULL AUTO_INCREMENT,
  `bucket_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`bucket_items_id`),
  UNIQUE INDEX `bucket_items_id_UNIQUE` (`bucket_items_id` ASC) VISIBLE,
  INDEX `bucket_item_bucket_id_idx` (`bucket_id` ASC) VISIBLE,
  INDEX `bucket_items_item_id_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `bucket_items_bucket_id`
    FOREIGN KEY (`bucket_id`)
    REFERENCES `shop`.`buckets` (`buket_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `bucket_items_item_id`
    FOREIGN KEY (`item_id`)
    REFERENCES `shop`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `shop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `orders_user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `orders_users_id_fk_idx` (`orders_user_id` ASC) VISIBLE,
  CONSTRAINT `orders_users_id_fk`
    FOREIGN KEY (`orders_user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
