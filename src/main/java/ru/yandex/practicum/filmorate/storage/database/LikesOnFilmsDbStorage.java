package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.storage.LikesOnFilmsStorage;

@Component
@Qualifier("likesDb")
public class LikesOnFilmsDbStorage implements LikesOnFilmsStorage {
    private JdbcTemplate jdbcTemplate;

    public LikesOnFilmsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(int id, int userId) {
        String query = "INSERT INTO films_users_liked VALUES(?, ?)";
        jdbcTemplate.update(query, id, userId);
    }

    @Override
    public void deleteLike(int id, int userId) {

        String query = "DELETE FROM films_users_liked WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(query, id, userId);
    }
}
