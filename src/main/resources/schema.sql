CREATE TABLE IF NOT EXISTS film
(
    id           int PRIMARY KEY,
    name         varchar(80),
    description  varchar(200),
    release_date date,
    duration int,
    rate double,
    likes int,
    genres varchar(20),
    mpa_rating varchar(20)

);

CREATE TABLE IF NOT EXISTS genre
(
    id varchar(20) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS film_genre
(
    genre_id varchar(20) NOT NULL,
    film_id  int         NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (id),
    FOREIGN KEY (film_id) REFERENCES film (id),
    UNIQUE (genre_id, film_id)
);

CREATE TABLE IF NOT EXISTS user
(
    user_id int NOT NULL PRIMARY KEY,
    email_id varchar(80) NOT NULL UNIQUE,
    login varchar(80) UNIQUE,
    name varchar(80),
    birthday date,
    friends int,
    films_liked int

);


CREATE TABLE IF NOT EXISTS film_genre
(
    genre_id varchar(20) NOT NULL,
    film_id  int         NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (id),
    FOREIGN KEY (film_id) REFERENCES film (id),
    UNIQUE (genre_id, film_id)
);

CREATE TABLE IF NOT EXISTS user_friends
(
    user_id int NOT NULL,
    friend_id  int         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (friend_id) REFERENCES user (user_id)
)