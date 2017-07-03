-- Set role levels
INSERT INTO role VALUES (NULL, 'guest');
INSERT INTO role VALUES (NULL, 'author');
INSERT INTO role VALUES (NULL, 'moderator');
INSERT INTO role VALUES (NULL, 'admin');
INSERT INTO role VALUES (NULL, 'owner');

-- Test post
--INSERT INTO posts (`title`, `summary`, `content`, `author`)
--SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
--FROM DUAL
--WHERE NOT EXISTS (SELECT * FROM posts);
