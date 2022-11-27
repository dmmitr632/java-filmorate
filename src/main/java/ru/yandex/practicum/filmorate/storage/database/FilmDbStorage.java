package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidIdOfFilmException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidMpaRatingException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.sql.PreparedStatement;
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

    public Film addFilm(@Valid @RequestBody Film film) {
        String query =
                "INSERT INTO films (name, description, release_date, duration, mpa_id)" + " VALUES(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{"id"});

            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setObject(3, film.getReleaseDate());
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getMpaId());
            return stmt;
        }, keyHolder);
        //
        film.setId(keyHolder.getKey().intValue());
        log.info("Film id {}", film.getId());


        Mpa rating = this.getRating(film.getId());
        film.setMpa(rating);

        HashSet<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
        film.setGenres(genres);
        validateFilm(film);
        return film;
    }

    @Override
    public Film editFilm(@Valid @RequestBody Film film) {
        String query = "MERGE INTO films(id, name, description, release_date, duration, rate, mpa_id)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getDuration(), film.getRate(), film.getMpaId());

        HashSet<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
        film.setGenres(genres);
        validateFilm(film);
        return film;
    }

    @Override
    public List<Film> viewAllFilms() {
        String query = ("SELECT * FROM films");
        log.info("Отображаем все фильмы");
        List<Film> films = jdbcTemplate.query(query, new FilmMapper());
        for (Film film : films) {
            Mpa rating = this.getRating(film.getId());
            film.setMpa(rating);
            HashSet<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
            film.setGenres(genres);
            System.out.println(film);
        }
        return films;
    }

    private HashSet<Genre> getGenreForFilmByFilmId(int filmId) {
        String query = "SELECT * FROM genres WHERE id IN (SELECT genre_id FROM films_genres WHERE id = ?)";
        return new HashSet<Genre>(jdbcTemplate.query(query, new GenreMapper(), filmId));
    }

    private Mpa getRating(int id) {

        String query = "SELECT * FROM mpa WHERE mpa.id IN(SELECT mpa_id FROM films WHERE id = ?)";
        return jdbcTemplate.query(query, new MpaMapper(), id).stream().findAny()
                .orElseThrow(() -> new InvalidMpaRatingException("Неверный рейтинг для id" + id));
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
        String query = "SELECT * FROM films WHERE id = ?";

        Film film = jdbcTemplate.query(query, new FilmMapper(), filmId).stream().findAny()
                .orElseThrow(() -> new InvalidIdOfFilmException("Фильма с id " + filmId + "не существует"));

        Mpa rating = this.getRating(film.getId());
        film.setMpa(rating);
        HashSet<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
        film.setGenres(genres);
        return film;
    }

    @Override
    public void validateFilm(Film film) {
        if (film.getId() < 0) {
            throw new InvalidIdOfFilmException("id < 0");
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

        if (film.getDuration() < 0) {
            throw new ValidationException("Duration of film is less than zero");
        }
        if (film.getMpa() == null) {
            throw new ValidationException("Film lacks Mpa rating");
        }
    }

}
