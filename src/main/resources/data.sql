-- Добавляем фильмы
INSERT
INTO films
VALUES (1, 'Фильм 1', 'Описание фильма 1 ', '2010-01-01', 120, 5.0, 1);

INSERT
INTO films
VALUES (2, 'Фильм 2', 'Описание фильма 2', '2015-01-01', 150, 9.0, 2);

-- Обновляем фильм
UPDATE films
SET films.name='Фильм 1 новое имя'
WHERE films.film_id = 1;

-- Удаляем фильм
DELETE
FROM films
WHERE films.film_id = 1;

-- Добавляем пользователей
INSERT
INTO users
VALUES (1, 'anna@gmail.com', 'anna', 'Anna', '2000-01-01');

INSERT
INTO users
VALUES (2, 'joe@gmail.com', 'joelogin', 'Joe', '2005-05-01');

INSERT
INTO users
VALUES (3, 'joe@gmail.com', 'joelogin2', 'Joe', '2005-05-01');


-- Обновляем пользователя
UPDATE users
SET users.name='Anna 2'
WHERE users.user_id = 1;

-- Удаляем пользователя
DELETE
FROM users
WHERE users.user_id = 3;

-- Односторонняя дружба
INSERT
INTO users_friends (user_id, friend_id)
VALUES (1, 2);

-- Без дружбы
DELETE
FROM users_friends
WHERE users_friends.user_id = 1
  AND users_friends.friend_id = 2;


-- Двусторонняя дружба

INSERT
INTO users_friends (user_id, friend_id)
VALUES (1, 2);

INSERT
INTO users_friends (user_id, friend_id)
VALUES (2, 1);


-- добавление лайка фильму
INSERT
INTO films_users_liked (user_id, film_id)
VALUES (1, 1);

-- удаление лайка у фильма
DELETE
FROM films_users_liked
WHERE films_users_liked.film_id = 1
  AND films_users_liked.user_id = 1;




