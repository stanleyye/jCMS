/*
 * MySQL script.
 * Create the database schema for the application.
 */

-- Role table
CREATE TABLE IF NOT EXISTS role (
    id INT unsigned NOT NULL auto_increment,
    role_name VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Users table
CREATE TABLE IF NOT EXISTS user (
    id INT unsigned NOT NULL auto_increment,
    creation_date TIMESTAMP DEFAULT NOW(),
    username VARCHAR(25) NOT NULL,
    email VARCHAR(80) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- User-Role table
CREATE TABLE IF NOT EXISTS user_role (
	id INT unsigned NOT NULL auto_increment,
	user_id INT unsigned NOT NULL,
	role_id INT unsigned NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user(id),
  UNIQUE (user_id),
	CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (role_id) REFERENCES role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Posts table
CREATE TABLE IF NOT EXISTS post (
    id INT unsigned NOT NULL auto_increment,
    publication_date TIMESTAMP DEFAULT NOW(),
    title VARCHAR(100) DEFAULT NULL,
    summary VARCHAR(255) DEFAULT NULL,
    content TEXT DEFAULT NULL,
    author_id INT unsigned NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
