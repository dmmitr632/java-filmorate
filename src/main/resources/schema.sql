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
