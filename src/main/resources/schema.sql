CREATE TABLE IF NOT EXISTS mpa
(
    id int PRIMARY KEY,
    name   varchar(80)
);


CREATE TABLE IF NOT EXISTS films
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    name          varchar(80),
    description   varchar(200),
    release_date  date,
    duration      int,
    rate          double,
    mpa_id int,
    FOREIGN KEY (mpa_id) REFERENCES mpa (id)
);



CREATE TABLE IF NOT EXISTS genres
(
    id   int PRIMARY KEY,
    name varchar(20)
);

CREATE TABLE IF NOT EXISTS films_genres
(
    id  int NOT NULL,
    genre_id int NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres (id),
    FOREIGN KEY (id) REFERENCES films (id),
    UNIQUE (id, genre_id)
);

CREATE TABLE IF NOT EXISTS users
(
    id  int         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email    varchar(80) NOT NULL UNIQUE,
    login    varchar(80) UNIQUE,
    name     varchar(80),
    birthday date
);

CREATE TABLE IF NOT EXISTS users_friends
(
    id   int NOT NULL,
    friend_id int NOT NULL,
    UNIQUE (id, friend_id),
    FOREIGN KEY (id) REFERENCES users (id),
    FOREIGN KEY (friend_id) REFERENCES users (id)
);


CREATE TABLE IF NOT EXISTS films_users_liked
(
    id int NOT NULL,
    user_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (id) REFERENCES films (id),
    UNIQUE (id, user_id)
);


