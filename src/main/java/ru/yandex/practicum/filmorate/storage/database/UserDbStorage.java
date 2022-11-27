package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@Qualifier("userDb")
public class UserDbStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User user) {
        String query = "INSERT INTO users(email, login, name, birthday) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{"id"});

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setObject(3, user.getName());
            stmt.setObject(3, user.getBirthday());
            return stmt;
        }, keyHolder);

        user.setId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public User editUser(User user) {
        String query = "MERGE INTO users(id, email, login, name, birthday)" + " VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());

        return user;
    }

    @Override
    public List<User> viewAllUsers() {
        String query = ("SELECT * FROM users");
        log.info("Выводим всех пользователей");
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public Map<Integer, User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public void validateUser(User user) {

    }
}
