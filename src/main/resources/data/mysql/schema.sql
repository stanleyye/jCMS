/*
 * MySQL script.
 * Create the database schema for the application.
 */

CREATE DATABASE IF NOT EXISTS jcms;
USE jcms;

SET FOREIGN_KEY_CHECKS = 0;

-- Role table
DROP TABLE IF EXISTS role;
CREATE TABLE role (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    roleName VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Users table
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    creationDate TIMESTAMP DEFAULT NOW(),
    username VARCHAR(25) DEFAULT NULL,
    email VARCHAR(80) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    userRole BIGINT(20) unsigned NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userRole) REFERENCES role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Posts table
DROP TABLE IF EXISTS post;
CREATE TABLE post (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    publicationDate TIMESTAMP DEFAULT NOW(),
    title VARCHAR(100) DEFAULT NULL,
    summary VARCHAR(255) DEFAULT NULL,
    content VARCHAR(255) DEFAULT NULL,
    authorId BIGINT(20) unsigned NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorId) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
