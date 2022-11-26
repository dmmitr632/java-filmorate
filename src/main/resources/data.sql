-- Добавляем рейтинги

MERGE
INTO mpa
    VALUES (1, 'G');

MERGE
INTO mpa
    VALUES (2, 'PG');

MERGE
INTO mpa
    VALUES (3, 'PG_13');

MERGE
INTO mpa
    VALUES (4, 'R');

MERGE
INTO mpa
    VALUES (5, 'NC_17');

-- Добавляем жанры фильмов

MERGE
INTO genres
    VALUES (1, 'Comedy');

MERGE
INTO genres
    VALUES (2, 'Drama');

MERGE
INTO genres
    VALUES (3, 'Cartoon');

MERGE
INTO genres
    VALUES (4, 'Thriller');

MERGE
INTO genres
    VALUES (5, 'Documentary');

MERGE
INTO genres
    VALUES (6, 'Action');


-- Добавляем пользователей
MERGE
INTO users
    VALUES (1, 'anna@gmail.com', 'anna', 'Anna', '2000-01-01');

MERGE
INTO users
    VALUES (2, 'joe@gmail.com', 'joelogin', 'Joe', '2005-05-01');

MERGE
INTO users
    VALUES (3, 'joe2@gmail.com', 'joelogin2', 'Joe', '2005-05-01');


-- Добавляем фильмы
MERGE
INTO films
    VALUES (1, 'Фильм 1', 'Описание фильма 1 ', '2010-01-01', 120, 5.0, 1);

MERGE
INTO films
    VALUES (2, 'Фильм 2', 'Описание фильма 2', '2015-01-01', 150, 9.0, 2);


MERGE
INTO films
    VALUES (3, 'Фильм 3', 'Описание фильма 3', '2012-01-01', 150, 8.2, 2);

-- Обновляем фильм
UPDATE films
SET films.name='Фильм 1 новое имя'
WHERE films.id = 1;

-- Удаляем фильм
DELETE
FROM films
WHERE films.id = 3;


-- Обновляем пользователя
UPDATE users
SET users.name='Anna 2'
WHERE users.id = 1;

-- Удаляем пользователя
DELETE
FROM users
WHERE users.id = 3;

-- Односторонняя дружба
MERGE
INTO users_friends KEY (id, friend_id)
    VALUES (1, 2);

-- Без дружбы
DELETE
FROM users_friends
WHERE users_friends.id = 1
  AND users_friends.friend_id = 2;


-- Двусторонняя дружба

MERGE
INTO users_friends KEY (id, friend_id)
    VALUES (1, 2);

MERGE
INTO users_friends KEY (id, friend_id)
    VALUES (2, 1);


-- добавление лайка фильму
MERGE
INTO films_users_liked KEY (id, user_id)
    VALUES (1, 1);

-- удаление лайка у фильма
DELETE
FROM films_users_liked
WHERE films_users_liked.id = 1
  AND films_users_liked.id = 1;

-- Добавляем жанры для фильмов
MERGE
INTO films_genres KEY (id, genre_id)
    VALUES (1, 3);

MERGE
INTO films_genres KEY (id, genre_id)
    VALUES (1, 2);

MERGE
INTO films_genres KEY (id, genre_id)
    VALUES (1, 6);

MERGE
INTO films_genres KEY (id, genre_id)
    VALUES (2, 1);

MERGE
INTO films_genres KEY (id, genre_id)
    VALUES (2, 4);

-- Добавляем лайки для фильмов

MERGE
INTO films_users_liked KEY (id, user_id)
    VALUES (1, 2);

MERGE
INTO films_users_liked KEY (id, user_id)
    VALUES (1, 1);

MERGE
INTO films_users_liked KEY (id, user_id)
    VALUES (2, 2);




