package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.mapper.MpaRatingMapper;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;
import ru.yandex.practicum.filmorate.storage.RatingStorage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Qualifier("mpaDb")
public class RatingDbStorage implements RatingStorage {
    private JdbcTemplate jdbcTemplate;

    public RatingDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FilmMpaRating getRatingById(int id) {
        String query = "SELECT * FROM mpa WHERE id = ?";
        return jdbcTemplate.query(query, new MpaRatingMapper(), id).stream().findAny()
                .orElseThrow(() -> new RuntimeException("Рейтинга с" + id + " не существует"));
    }

    @Override
    public List<FilmMpaRating> getMpaRatings() {
        String query = "SELECT * FROM mpa";
        List<FilmMpaRating> ratings = new ArrayList<>(jdbcTemplate.query(query, new MpaRatingMapper()));
        return ratings;
    }
}
