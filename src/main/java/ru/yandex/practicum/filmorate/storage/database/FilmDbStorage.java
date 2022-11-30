package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.Constants.CINEMA_BIRTHDAY;
import static ru.yandex.practicum.filmorate.Constants.MAX_DESCRIPTION_LENGTH;

@Slf4j
@Component
@Qualifier("filmDb")
public class FilmDbStorage implements FilmStorage {
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Film addFilm(@Valid @RequestBody Film film) throws ValidationException {

        validateFilm(film);

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

        film.setId(keyHolder.getKey().intValue());
        Mpa rating = this.getRating(film.getId());
        film.setMpa(rating);

        this.addFilmGenre(film);

        return film;
    }

    @Override
    public Film editFilm(@Valid @RequestBody Film film) throws ValidationException, NotFoundException {
        validateFilm(film);

        String query = "MERGE INTO films(id, name, description, release_date, duration, rate, mpa_id)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";
        findFilmByIdInDb(film.getId());

        jdbcTemplate.update(query, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getDuration(), film.getRate(), film.getMpaId());

        this.addFilmGenre(film);
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
            ArrayList<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
            film.setGenres(genres);
        }
        return films;
    }

    private ArrayList<Genre> getGenreForFilmByFilmId(int filmId) {
        findFilmByIdInDb(filmId);
        String query = "SELECT * FROM genres WHERE id IN (SELECT genre_id FROM films_genres WHERE id = ?)";
        return (ArrayList<Genre>) jdbcTemplate.query(query, new GenreMapper(), filmId);
    }

    private Mpa getRating(int id) {
        findFilmByIdInDb(id);
        String query = "SELECT * FROM mpa WHERE mpa.id IN(SELECT mpa_id FROM films WHERE id = ?)";
        return jdbcTemplate.query(query, new MpaMapper(), id).stream().findAny()
                .orElseThrow(() -> new ValidationException("Неверный рейтинг для id" + id));
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
        Film film = findFilmByIdInDb(filmId);
        Mpa rating = this.getRating(film.getId());
        film.setMpa(rating);
        ArrayList<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
        film.setGenres(genres);
        return film;
    }

    @Override
    public void validateFilm(Film film) {
        if (film.getId() != null) {
            if (film.getId() < 0) {
                throw new ValidationException("id < 0");
            }
        }

        if (film.getName().equals("")) {
            log.info("Empty film name {}", film.getName());
            throw new ValidationException("film lacks name");
        }

        if (film.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            log.info("Too long film description, {} chars", film.getDescription().length());
            throw new ValidationException("too long film description");
        }

        if (film.getReleaseDate().isBefore(CINEMA_BIRTHDAY)) {
            log.info("Wrong film date (before 1895-12-28) {}", film.getReleaseDate());
            throw new ValidationException("Film releaseDate before 1895-12-28");
        }

        if (film.getDuration() < 0) {
            throw new ValidationException("Duration of film is less than zero");
        }
        if (film.getMpa() == null) {
            throw new ValidationException("Film lacks Mpa rating");
        }
    }

    private Film findFilmByIdInDb(int id) throws NotFoundException {
        String query = "SELECT * FROM films WHERE id = ?";
        return jdbcTemplate.query(query, new FilmMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("Film not found")));
    }

    private void addFilmGenre(Film film) {
        String queryAddGenreFilm = "INSERT INTO films_genres(id, genre_id) VALUES (?, ?)";
        if (film.getGenres() != null) {
            List<Genre> genres = new ArrayList<>(film.getGenres());
            for (Genre genre : genres) {
                jdbcTemplate.update(queryAddGenreFilm, film.getId(), genre.getId());
            }
        } else {
            jdbcTemplate.update(queryAddGenreFilm, film.getId(), null);
        }
    }
}
