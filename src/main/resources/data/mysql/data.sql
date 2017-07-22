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
 */
INSERT INTO user_role (user_id, role_id)
SELECT * FROM (
	SELECT user.id as user_id, role.id as role_id
	FROM user, role
	WHERE user.username = 'admin' AND role.role_name = 'owner'
) AS tmp
WHERE NOT EXISTS (
	SELECT user_role.user_id, user_role.role_id
	FROM user_role,
			 (
				 SELECT user.id AS admin_user_id, role.id AS admin_role_id
				 FROM user, role
				 WHERE user.username = 'admin' AND role.role_name = 'admin'
				 LIMIT 1
			 ) AS admin
	WHERE user_role.user_id = admin.admin_user_id
	AND user_role.role_id = admin.admin_role_id
) LIMIT 1;

/*
 * Create test posts
 */
INSERT INTO post (title, summary, content, author_id)
VALUES (
	'Test 1',
	'Test Summary',
	CONCAT(
		'Lorem ipsum dolor sit amet, probo solet eam id, odio sensibus vulputate ',
		'at sea. Fabulas oportere posidonium has ei, an autem petentium mea. Cum ',
		'posse saperet in, et qui nonumy tacimates dignissim, utroque expetendis ',
		'usu ut. In error legimus pro, et vero epicurei assueverit eos. <br>',
		'Eirmod dolorem gubergren sed ea.'
	),
	(
		SELECT user.id FROM user WHERE user.username = 'admin' LIMIT 1
	)
);

INSERT INTO post (title, summary, content, author_id)
VALUES (
	'Test 2',
	'Test Summary 2',
	CONCAT(
		'<b>Lorem ipsum</b> dolor sit amet, his eu choro officiis intellegebat, ',
		'accusamus salutatus ei has. Cum iudicabit assentior adolescens ad, vel ',
		'vitae iudico partiendo id. Fuisset albucius temporibus cu sea, falli ',
		'eligendi quaerendum vim an. Tollit fuisset neglegentur sea in, mel ',
		'debitis euripidis id. Sed propriae honestatis ex, eos diceret.'
	),
	(
		SELECT user.id FROM user WHERE user.username = 'admin' LIMIT 1
	)
);

INSERT INTO post (title, summary, content, author_id)
VALUES (
	'Test 3',
	'Test Summary 3',
	CONCAT(
		'Lorem ipsum dolor sit amet, at mei copiosae corrumpit prodesset. Per ',
		'corrumpit constituam in. Erat oporteat ei his. Id pri tation delectus ',
		'antiopam, eos tota concludaturque ad. An vis maiorum intellegebat, mea ',
		'quas percipitur assueverit id. <br> Nec utinam qualisque consectetuer ',
		'ei, pro ex iudico dolorum, ea pri vide veniam accusamus.'
	),
	(
		SELECT user.id FROM user WHERE user.username = 'admin' LIMIT 1
	)
);
