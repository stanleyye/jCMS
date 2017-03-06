/*
 * MySQL script.
 * Create the database schema for the application.
 */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `id` BIGINT(20) unsigned NOT NULL auto_increment,
  `publicationDate` TIMESTAMP DEFAULT NOW(),
  `title` VARCHAR(100) DEFAULT NULL,
  `summary` VARCHAR(255) DEFAULT NULL,
  `content` VARCHAR(255) DEFAULT NULL,
  `author` INT(100) NOT NULL DEFAULT 0,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
