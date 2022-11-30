package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.ArrayList;

@Component
@Qualifier("genreDb")
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<Genre> getAllGenres() {
        String query = "SELECT * FROM genres";
        return (ArrayList<Genre>) jdbcTemplate.query(query, new GenreMapper());
    }

    @Override
    public Genre getGenreById(int id) {
        String query = "SELECT * FROM genres WHERE id = ?";
        return jdbcTemplate.query(query, new GenreMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
    }
}
