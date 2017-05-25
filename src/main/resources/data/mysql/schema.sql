/*
 * MySQL script.
 * Create the database schema for the application.
 */

CREATE TABLE IF NOT EXISTS `posts` (
  `id` BIGINT(20) unsigned NOT NULL auto_increment,
  `publicationDate` TIMESTAMP DEFAULT NOW(),
  `title` VARCHAR(100) DEFAULT NULL,
  `summary` VARCHAR(255) DEFAULT NULL,
  `content` VARCHAR(255) DEFAULT NULL,
  `author` BIGINT(20) NOT NULL DEFAULT 0,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`author`) REFERENCES users(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT(20) unsigned NOT NULL auto_increment,
  `creationDate` TIMESTAMP DEFAULT NOW(),
  `username` VARCHAR(25) DEFAULT NULL,
  `email` VARCHAR(80) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `userLevel` INT NOT NULL DEFAULT 0,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;