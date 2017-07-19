/*
 * Role levels
 */
INSERT INTO role (role_name)
SELECT * FROM (
	SELECT 'author' as role_name
	) AS tmp
WHERE NOT EXISTS (
	SELECT role_name from role WHERE role_name = 'author'
) LIMIT 1;

INSERT INTO role (role_name)
SELECT * FROM (
	SELECT 'admin' as role_name
	) AS tmp
WHERE NOT EXISTS (
	SELECT role_name from role WHERE role_name = 'admin'
) LIMIT 1;

INSERT INTO role (role_name)
SELECT * FROM (
	SELECT 'owner' as role_name
	) AS tmp
WHERE NOT EXISTS (
	SELECT role_name from role WHERE role_name = 'owner'
) LIMIT 1;

/*
 * Create an admin user
 */
INSERT INTO user (creation_date, username, email, password)
SELECT * FROM (
	SELECT NOW() as creation_date,
		   'admin' as username,
		   'admin@admin' as email,
		   'password' as password
	) AS tmp
WHERE NOT EXISTS (
    SELECT username, email FROM user WHERE username = 'admin' OR email = 'admin@admin'
) LIMIT 1;

/*
 * Set the admin level for the admin user
 * TODO: only insert if the admin user doesn't have a role. Right now, it is making multiple copies
 */
REPLACE INTO user_role (user_id, role_id)
SELECT user.id, role.id
FROM user, role
WHERE user.username = 'admin' AND role.role_name = 'admin';

-- Test post
--INSERT INTO posts (`title`, `summary`, `content`, `author`)
--SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
--FROM DUAL
--WHERE NOT EXISTS (SELECT * FROM posts);
