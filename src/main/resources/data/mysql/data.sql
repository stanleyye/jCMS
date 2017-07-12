-- Set role levels
INSERT INTO role VALUES (NULL, 'guest');
INSERT INTO role VALUES (NULL, 'author');
INSERT INTO role VALUES (NULL, 'moderator');
INSERT INTO role VALUES (NULL, 'admin');
INSERT INTO role VALUES (NULL, 'owner');

-- Create an admin user
INSERT INTO user (creation_date, username, email, password)
SELECT * FROM (
	SELECT NOW() as creation_date,
		   'admin' as username,
		   'admin@admin' as email,
		   'password' as password)
	AS tmp
WHERE NOT EXISTS (
    SELECT username, email FROM user WHERE username = 'admin' OR email = 'admin@admin'
) LIMIT 1;

-- Set the admin level for the admin user
REPLACE INTO user_roles
SELECT user.id, role.id
FROM user, role
WHERE user.username='admin' AND role.role_name='admin';

-- Test post
--INSERT INTO posts (`title`, `summary`, `content`, `author`)
--SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
--FROM DUAL
--WHERE NOT EXISTS (SELECT * FROM posts);
