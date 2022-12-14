-- Добавляем рейтинги

MERGE
INTO mpa
    VALUES (1, 'G');

MERGE
INTO mpa
    VALUES (2, 'PG');

MERGE
INTO mpa
    VALUES (3, 'PG-13');

MERGE
INTO mpa
    VALUES (4, 'R');

MERGE
INTO mpa
    VALUES (5, 'NC-17');

-- Добавляем жанры фильмов

MERGE
INTO genres
    VALUES (1, 'Комедия');

MERGE
INTO genres
    VALUES (2, 'Драма');

MERGE
INTO genres
    VALUES (3, 'Мультфильм');

MERGE
INTO genres
    VALUES (4, 'Триллер');

MERGE
INTO genres
    VALUES (5, 'Документальный');

MERGE
INTO genres
    VALUES (6, 'Боевик');



--
--
--
-- -- Добавляем пользователей
-- MERGE
-- INTO users
--     VALUES (1, 'anna@gmail.com', 'anna', 'Anna', '2000-01-01');
--
-- MERGE
-- INTO users
--     VALUES (2, 'joe@gmail.com', 'joelogin', 'Joe', '2005-05-01');
--
-- MERGE
-- INTO users
--     VALUES (3, 'joe2@gmail.com', 'joelogin2', 'Joe', '2005-05-01');
--
--
--
--
-- -- Добавляем фильмы
-- MERGE
-- INTO films
--     VALUES (1, 'Фильм 1', 'Описание фильма 1 ', '2010-01-01', 120, 5.0, 4);
--
-- MERGE
-- INTO films
--     VALUES (2, 'Фильм 2', 'Описание фильма 2', '2015-01-01', 150, 9.0, 2);
--
--
-- MERGE
-- INTO films
--     VALUES (3, 'Фильм 3', 'Описание фильма 3', '2012-01-01', 150, 8.2, 3);
--
--
-- -- Односторонняя дружба
-- MERGE
-- INTO users_friends KEY (id, friend_id)
--     VALUES (1, 2);
--
-- -- Без дружбы
-- DELETE
-- FROM users_friends
-- WHERE users_friends.id = 1
--   AND users_friends.friend_id = 2;
--
--
-- --Двусторонняя дружба
--
-- MERGE
-- INTO users_friends KEY (id, friend_id)
--     VALUES (1, 2);
--
-- MERGE
-- INTO users_friends KEY (id, friend_id)
--     VALUES (2, 1);
--
--
-- -- добавление лайка фильму
-- MERGE
-- INTO films_users_liked KEY (id, user_id)
--     VALUES (1, 1);
--
-- -- удаление лайка у фильма
-- DELETE
-- FROM films_users_liked
-- WHERE films_users_liked.id = 1
--   AND films_users_liked.id = 1;
--
-- -- Добавляем жанры для фильмов
-- MERGE
-- INTO films_genres KEY (id, genre_id)
--     VALUES (1, 3);
--
-- MERGE
-- INTO films_genres KEY (id, genre_id)
--     VALUES (1, 2);
--
-- MERGE
-- INTO films_genres KEY (id, genre_id)
--     VALUES (1, 6);
--
-- MERGE
-- INTO films_genres KEY (id, genre_id)
--     VALUES (2, 1);
--
-- MERGE
-- INTO films_genres KEY (id, genre_id)
--     VALUES (2, 4);
--
-- -- Добавляем лайки для фильмов
--
-- MERGE
-- INTO films_users_liked KEY (id, user_id)
--     VALUES (1, 2);
--
-- MERGE
-- INTO films_users_liked KEY (id, user_id)
--     VALUES (1, 1);
--
-- MERGE
-- INTO films_users_liked KEY (id, user_id)
--     VALUES (2, 2);




