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
CREATE TABLE `films` (
  `film_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(80),
  `description` varchar(200),
  `release_date` date,
  `duration` int,
  `rate` double
);

CREATE TABLE `genres` (
  `genre_id` int PRIMARY KEY,
  `genre_name` varchar(20)
);

CREATE TABLE `films_genres` (
  `genre_id` int NOT NULL,
  `film_id` int NOT NULL
);

CREATE TABLE `users` (
  `user_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `email` varchar(80) UNIQUE NOT NULL,
  `login` varchar(80) UNIQUE,
  `name` varchar(80),
  `birthday` date
);

CREATE TABLE `users_friends` (
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL
);

CREATE TABLE `films_users_liked` (
  `user_id` int NOT NULL,
  `film_id` int NOT NULL
);

CREATE TABLE `mpa_rating` (
  `mpa_rating_id` varchar(20) PRIMARY KEY AUTO_INCREMENT,
  `rating_name` varchar(20),
  `film_id` int
);

CREATE UNIQUE INDEX `films_genres_index_0` ON `films_genres` (`genre_id`, `film_id`);

CREATE UNIQUE INDEX `users_friends_index_1` ON `users_friends` (`user_id`, `friend_id`);

CREATE UNIQUE INDEX `films_users_liked_index_2` ON `films_users_liked` (`user_id`, `film_id`);

ALTER TABLE `films_genres` ADD FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`);

ALTER TABLE `films_genres` ADD FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`);

ALTER TABLE `users_friends` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `users_friends` ADD FOREIGN KEY (`friend_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `films_users_liked` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `films_users_liked` ADD FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`);

ALTER TABLE `mpa_rating` ADD FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`);
```

![Схема БД](./src/main/resources/schema.png)