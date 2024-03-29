CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use test;

CREATE TABLE IF NOT EXISTS `author` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45),
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `category` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45),
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `book` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`releaseTime` DATETIME,
	`releaseDate` DATE,
	`price` DECIMAL(19,5),
	`name` VARCHAR(45),
	`authorId` BIGINT,
	`categoryId` BIGINT,
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`),
    INDEX index_price_id (`price`, `id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;

INSERT INTO `book` (`releaseTime`, `releaseDate`, `price`, `name`, `authorId`, `categoryId`, `createdTime`, `updatedTime`)
SELECT
    DATE_ADD('2021-01-01', INTERVAL RAND() * DATEDIFF('2022-12-31', '2021-01-01') DAY),
    DATE_ADD('2021-01-01', INTERVAL RAND() * DATEDIFF('2022-12-31', '2021-01-01') DAY),
    ROUND(RAND() * 1000, 5),
    CONCAT('Book', FLOOR(RAND() * 10000)),
    FLOOR(RAND() * 1000),
    FLOOR(RAND() * 100),
    NOW(),
    NOW()
FROM
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t1,
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) t2
    LIMIT 10000;

SELECT * FROM book e
WHERE e.price > ?0 OR (e.price = ?0 AND e.id > ?1)
ORDER BY e.price DESC, e.id DESC LIMIT 100
