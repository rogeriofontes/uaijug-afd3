ALTER TABLE `product_order`
ADD COLUMN `total` INT(11) NOT NULL AFTER `client_id`;