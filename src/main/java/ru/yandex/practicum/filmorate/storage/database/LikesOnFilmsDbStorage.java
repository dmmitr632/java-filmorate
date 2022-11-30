package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.storage.LikesOnFilmsStorage;

@Component
@Qualifier("likesDb")
public class LikesOnFilmsDbStorage implements LikesOnFilmsStorage {
    private final JdbcTemplate jdbcTemplate;

    public LikesOnFilmsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(int id, int userId) {
        findFilmByIdInDb(id);
        findUserByIdInDb(userId);
        String query = "INSERT INTO films_users_liked VALUES(?, ?)";
        jdbcTemplate.update(query, id, userId);
        String queryPlusLike = "UPDATE films SET rate = rate +1 WHERE id = ?";
        jdbcTemplate.update(queryPlusLike, id);
    }

    @Override
    public void deleteLike(int id, int userId) {
        findFilmByIdInDb(id);
        findUserByIdInDb(userId);
        String query = "DELETE FROM films_users_liked WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(query, id, userId);
        String queryMinusLike = "UPDATE films SET rate = rate  - 1 WHERE id = ?";
        jdbcTemplate.update(queryMinusLike, id);
    }

    private void findFilmByIdInDb(int id) throws NotFoundException {
        String query = "SELECT * FROM films WHERE id = ?";
        jdbcTemplate.query(query, new FilmMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("Film not found")));
    }

    private void findUserByIdInDb(int id) throws NotFoundException {
        String query = "SELECT * FROM users WHERE id = ?";
        jdbcTemplate.query(query, new UserMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("User not found")));
    }
}
