/*
 * MySQL script.
 * Create the database schema for the application.
 */

-- Role table
CREATE TABLE IF NOT EXISTS role (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    roleName VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Users table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    creationDate TIMESTAMP DEFAULT NOW(),
    username VARCHAR(25) DEFAULT NULL,
    email VARCHAR(80) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    userRole BIGINT(20) unsigned NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `FK4cdd7f450r33fl1uc76qlwih4` FOREIGN KEY (userRole) REFERENCES role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- User-

-- Posts table
CREATE TABLE IF NOT EXISTS post (
    id BIGINT(20) unsigned NOT NULL auto_increment,
    publicationDate TIMESTAMP DEFAULT NOW(),
    title VARCHAR(100) DEFAULT NULL,
    summary VARCHAR(255) DEFAULT NULL,
    content VARCHAR(255) DEFAULT NULL,
    authorId BIGINT(20) unsigned NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT `FK12njtf8e0jmyb45lqfpt6ad89` FOREIGN KEY (authorId) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

