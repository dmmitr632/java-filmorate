package ru.yandex.practicum.filmorate.storage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidIdOfFilmException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
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


        String sqlQuery = ("SELECT * FROM films");
        return jdbcTemplate.query(sqlQuery, new RowMapper<Film>() {
                    @Override
                    public Film mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        //FilmMpaRating filmMpaRating = rs.getString("");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
                        int duration = rs.getInt("duration");
                        int rate =rs.getInt("rate");
                        Film film = Film.builder().id(id).name(name).description(description).
                                releaseDate(releaseDate).duration(duration).rate(rate).build();
                        log.info("Список фильмов получен");
                        return film;
                    }

        });
    }


    @Override
    public Map<Integer, Film> getFilms() {
        return null;
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
