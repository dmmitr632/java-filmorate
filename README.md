# java-filmorate

Описание таблиц БД
---
Тут 7 таблиц:
films, users - данные фильмов/пользователей, genres - жанры

films_users_liked - лайкнутые пользователями фильмы. таблица для соединения многих-ко-многим. У каждого фильма могут быть лайки от множества пользователей, у пользователей могут быть лайки ко множеству фильмов.

films_genres - жанры фильмов. также таблица для м-н-м. У каждого фильма может быть множества тегов, и наоборот.

mpa_rating - MPA-рейтинги. соединение 1 ко многим. У каждого фильма может быть только 1 рейтинг, у каждого рейтинга множество фильмов.

users_friends - таблица для отслеживания статуса дружбы/подписки. Чтобы быть "другом", нужно чтобы в таблице были 2 записи "А подписан на Б", и "Б подписан на А", если одной из записей нет, это статус "подписка", если обоих записей нет, то пользователи не связаны дружбой.


Код для создания базы данных:
```
CREATE TABLE IF NOT EXISTS films
(
    film_id           int PRIMARY KEY AUTO_INCREMENT,
    name         varchar(80),
    description  varchar(200),
    release_date date,
    duration     int,
    rate         double,
    likes        int,
    genres       varchar(20),
    mpa_rating   varchar(20)

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
    birthday    date,
    friends     int,
    films_liked int

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
    film_id int,
    FOREIGN KEY (film_id) REFERENCES films (film_id)
);
```

![Схема БД](./src/main/resources/scheme.png)