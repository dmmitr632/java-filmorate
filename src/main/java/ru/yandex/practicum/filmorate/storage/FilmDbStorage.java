package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidIdOfFilmException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.mapper.MpaRatingMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;
import ru.yandex.practicum.filmorate.model.Genre;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Qualifier("filmDb")
public class FilmDbStorage implements FilmStorage {
    private static final LocalDate CINEMA_BIRTHDAY = LocalDate.of(1895, Month.DECEMBER, 28);
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film addFilm(Film film) {
        return null;
    }

    @Override
    public Film editFilm(Film film) {
        return null;
    }

    @Override
    public List<Film> viewAllFilms() {
        String query = ("SELECT * FROM films");
        log.info("Отображаем все фильмы");
        List<Film> films = jdbcTemplate.query(query, new FilmMapper());
        System.out.println(films);
        for (Film film : films) {
            FilmMpaRating rating = this.getMpaRating(film.getId());
            film.setFilmMpaRating(rating);
            HashSet<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
            film.setFilmGenres(genres);
            System.out.println(film);
        }
        return films;
    }

    private HashSet<Genre> getGenreForFilmByFilmId(long filmId) {
        String query = "SELECT * FROM genres WHERE genre_id IN (SELECT genre_id FROM films_genres WHERE film_id = ?)";
        return new HashSet<Genre>(jdbcTemplate.query(query, new GenreMapper(), filmId));
    }

    private FilmMpaRating getMpaRating(long ratingId) {
        //String query = "SELECT rating_name FROM mpa_rating WHERE mpa_rating_id = ?";
        String query = "SELECT * FROM films f, mpa_rating m where f.MPA_RATING_ID = m.MPA_RATING_ID AND FILM_ID = ?";
        return jdbcTemplate.query(query, new MpaRatingMapper(), ratingId).get(0);
    }

    @Override
    public Map<Integer, Film> getFilms() {
        List<Film> films = this.viewAllFilms();
        Map<Integer, Film> filmsMap = new HashMap<>();
        for (Film film : films) {
            filmsMap.put(film.getId(), film);
        }
        return filmsMap;
    }

    @Override
    public Film getFilmById(int filmId) {
        return null;
    }

    @Override
    public void validateFilm(Film film) {
        if (film.getId() < 0) {
            throw new InvalidIdOfFilmException();
        }

        if (film.getName().equals("")) {
            log.info("Empty film name {}", film.getName());
            throw new InvalidNameException("film lacks name");
        }

        if (film.getDescription().length() > 200) {
            log.info("Too long film description, {} chars", film.getDescription().length());
            throw new InvalidDescriptionException("too long film description");
        }

        if (film.getReleaseDate().isBefore(CINEMA_BIRTHDAY)) {
            log.info("Wrong film date (before 1895-12-28) {}", film.getReleaseDate());
            throw new InvalidReleaseDateException("Film releaseDate before 1895-12-28");
        }
    }

    @Override
    public int generateId() {
        return 0;
    }
}
