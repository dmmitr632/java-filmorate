-- CREATE TABLE IF NOT EXISTS mpa_rating
-- (
--     mpa_rating_id varchar(80) PRIMARY KEY,
--     rating_name   varchar(80)
-- );

-- [SELECT rating_name FROM mpa_rating WHERE mpa_rating_id = ?];
-- nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Column "mpa_rating_id" not found


[Film(id=1, filmMpaRating=NULL, name=Фильм 1 новое имя, description=Описание фильма 1 , releaseDate=2010-01-01, duration=120, rate=5, filmGenres=NULL),
Film(id=2, filmMpaRating=NULL, name=Фильм 2, description=Описание фильма 2, releaseDate=2015-01-01, duration=150, rate=9, filmGenres=NULL),
Film(id=4, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=5, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=6, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=7, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=8, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=9, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL),
Film(id=10, filmMpaRating=NULL, name=nisi eiusmod, description=adipisicing, releaseDate=1967-03-25, duration=100, rate=0, filmGenres=NULL)]



Film(id=1, filmMpaRating=FilmMpaRating(id=1, name=Фильм 1 новое имя), name=Фильм 1 новое имя, description=Описание фильма 1 , releaseDate=2010-01-01, duration=120, rate=5, filmGenres=[Genre(id=2, name=Drama), Genre(id=6, name=ACTION), Genre(id=3, name=Cartoon)])
Film(id=2, filmMpaRating=FilmMpaRating(id=2, name=Фильм 2), name=Фильм 2, description=Описание фильма 2, releaseDate=2015-01-01, duration=150, rate=9, filmGenres=[Genre(id=4, name=Thriller), Genre(id=1, name=Comedy)])