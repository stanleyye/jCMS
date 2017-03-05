/*
 * MySQL script.
 * Create the database schema for the application.
 */

DROP TABLE IF EXISTS `Post`;

CREATE TABLE `Post` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `publicationDate` datetime NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `author` int(100) NOT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
