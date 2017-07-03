-- Set role levels
INSERT INTO role(id, roleName) VALUES (NULL, 'guest');
INSERT INTO role(id, roleName) VALUES (NULL, 'author');
INSERT INTO role(id, roleName) VALUES (NULL, 'moderator');
INSERT INTO role(id, roleName) VALUES (NULL, 'admin');
INSERT INTO role(id, roleName) VALUES (NULL, 'owner');

-- Test post
--INSERT INTO posts (`title`, `summary`, `content`, `author`)
--SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
--FROM DUAL
--WHERE NOT EXISTS (SELECT * FROM posts);
