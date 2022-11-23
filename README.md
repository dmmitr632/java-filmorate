# java-filmorate

Описание таблиц БД
---
Тут 7 таблиц:
films, users - данные фильмов/пользователей, genres - жанры

films_users_liked - лайкнутые пользователями фильмы. таблица для соединения многих-ко-многим. У каждого фильма могут быть лайки от множества пользователей, у пользователей могут быть лайки ко множеству фильмов.

films_genres - жанры фильмов. также таблица для м-н-м. У каждого фильма может быть множества тегов, и наоборот.

mpa_rating - MPA-рейтинги. соединение 1 ко многим. У каждого фильма может быть только 1 рейтинг, у каждого рейтинга множество фильмов.

users_friends - таблица для отслеживания статуса дружбы/подписки. Чтобы быть "другом", нужно чтобы в таблице были 2 записи "А подписан на Б", и "Б подписан на А", если одной из записей нет, это статус "подписка", если обоих записей нет, то пользователи не связаны дружбой.


Код для создания базы данных schema.sql:
```
CREATE TABLE IF NOT EXISTS films
(
    film_id           int PRIMARY KEY AUTO_INCREMENT,
    name         varchar(80),
    description  varchar(200),
    release_date date,
    duration     int,
    rate         double,
    mpa_rating_id int,
    FOREIGN KEY (mpa_rating_id)  REFERENCES mpa_rating (mpa_rating_id)
);

CREATE TABLE IF NOT EXISTS genres
(
    genre_id int PRIMARY KEY,
    genre_name varchar(20)
);

CREATE TABLE IF NOT EXISTS films_genres
(
    genre_id int NOT NULL,
    film_id  int         NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id),
    FOREIGN KEY (film_id) REFERENCES films (film_id),
    UNIQUE (genre_id, film_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id     int         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email    varchar(80) NOT NULL UNIQUE,
    login       varchar(80) UNIQUE,
    name        varchar(80),
    birthday    date
);

CREATE TABLE IF NOT EXISTS users_friends
(
    user_id   int NOT NULL,
    friend_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (friend_id) REFERENCES users (user_id),
    UNIQUE (user_id, friend_id)
);


CREATE TABLE IF NOT EXISTS films_users_liked
(
    user_id int NOT NULL,
    film_id  int  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (film_id) REFERENCES films (film_id),
    UNIQUE (user_id, film_id)
);

CREATE TABLE IF NOT EXISTS mpa_rating
(
    mpa_rating_id varchar(20) PRIMARY KEY AUTO_INCREMENT,
    rating_name varchar(20),

);
```

Код для заполнения тестовыми данными(примеры запросов) data.sql

```
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


-- Добавляем лайк
INSERT
INTO films_users_liked (user_id, film_id)
VALUES (1, 1);

-- Удаляем лайк
DELETE
FROM films_users_liked
WHERE films_users_liked.film_id = 1
AND films_users_liked.user_id = 1;
```


![Схема БД](./src/main/resources/schema.png)