-- Set role levels
INSERT INTO role VALUES (NULL, 'guest');
INSERT INTO role VALUES (NULL, 'author');
INSERT INTO role VALUES (NULL, 'moderator');
INSERT INTO role VALUES (NULL, 'admin');
INSERT INTO role VALUES (NULL, 'owner');

-- Create an admin user
INSERT INTO user VALUES (NULL, NOW(), 'admin', 'admin@admin', 'password');
-- Set the admin level for the user
INSERT INTO user_roles
	SELECT username, id FROM user, user_roles
		WHERE username = 'admin', role_name = 'admin';


-- Test post
--INSERT INTO posts (`title`, `summary`, `content`, `author`)
--SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
--FROM DUAL
--WHERE NOT EXISTS (SELECT * FROM posts);
