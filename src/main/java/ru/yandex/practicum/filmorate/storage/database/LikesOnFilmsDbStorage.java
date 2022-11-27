package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.storage.LikesOnFilmsStorage;

public class LikesOnFilmsDbStorage implements LikesOnFilmsStorage {
    private JdbcTemplate jdbcTemplate;

    public LikesOnFilmsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(int filmId, int userId) {

    }

    @Override
    public void deleteLike(int filmId, int userId) {

    }
}
