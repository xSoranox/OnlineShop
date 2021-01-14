CREATE DATABASE IF NOT EXISTS onlineshop;
USE onlineshop;

CREATE TABLE IF NOT EXISTS Products (
ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
Name varchar(100) NOT NULL,
Category varchar(255) NOT NULL,
PriceBeforeDiscount decimal (4,2) NOT NULL,
Discount decimal (4,2) DEFAULT NULL,
EndPrice decimal (4,2) NOT NULL,
IsInEditor boolean NOT NULL DEFAULT 0,
CreatedDate timestamp NULL DEFAULT current_timestamp,
PRIMARY KEY (ID)
) ENGINE = InnoDB
AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ShoppingCarts (
CartID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
Name varchar(100) NOT NULL,
CreatedDate timestamp NULL DEFAULT current_timestamp,
PRIMARY KEY (CartID)
) ENGINE = InnoDB
AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS CartProducts (
ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
Name varchar(100) NOT NULL,
Category varchar(255) NOT NULL,
PriceBeforeDiscount decimal (4,2) NOT NULL,
Discount decimal (4,2) DEFAULT NULL,
EndPrice decimal (4,2) NOT NULL,
CreatedDate timestamp NULL DEFAULT current_timestamp,
CartID bigint(20) unsigned NOT NULL,
OriginalProductId bigint(20) NOT NULL,
Quantity INT DEFAULT 1,
PRIMARY KEY (ID),
CONSTRAINT CartProducts_ibfk_1 FOREIGN KEY (CartID) REFERENCES ShoppingCarts(CartID)
) ENGINE = InnoDB
AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE Login (
Username varchar(45) NOT NULL,
Password varchar(45) NOT NULL,
PRIMARY KEY(Username)
) ENGINE = InnoDB
AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;
