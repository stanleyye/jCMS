INSERT INTO posts (`title`, `summary`, `content`, `author`)
SELECT 'Test post 2!', 'sdfasd', 'usefr', 1
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM posts);
