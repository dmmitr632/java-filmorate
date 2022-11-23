package ru.yandex.practicum.filmorate.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Map;

@Component
@Qualifier("filmDb")
public class FilmDbStorage implements FilmStorage {

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
        return null;
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

    }

    @Override
    public int generateId() {
        return 0;
    }
}
