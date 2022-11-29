package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        if (user.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant().isAfter(Instant.now())) {
            log.info("Wrong birthday date {}", user.getBirthday());
            throw new InvalidBirthdayException("Birthday in the future");
        }
        //аналогично, почему валидация не работает?

        String query = "INSERT INTO users(email, login, name, birthday) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{"id"});

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setObject(3, user.getName());
            stmt.setObject(4, user.getBirthday());
            return stmt;
        }, keyHolder);

        user.setId(keyHolder.getKey().intValue());
        validateUser(user);
        return user;
    }

    @Override
    public User editUser(User user) {
        validateUser(user);
        String query = "MERGE INTO users(id, email, login, name, birthday)" + " VALUES(?, ?, ?, ?, ?)";
        checkIfUserIdPresent(user.getId());

        jdbcTemplate.update(query, user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());
        checkIfUserIdPresent(user.getId());
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
        List<User> users = this.viewAllUsers();
        Map<Integer, User> usersMap = new HashMap<>();
        for (User user: users) {
            usersMap.put(user.getId(), user);
        }
        return usersMap;
    }

    @Override
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(query, new UserMapper(), userId).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("User not found")));
    }

    @Override
    public void validateUser(User user) {
        if (user.getId() < 0) {
            throw new InvalidIdOfUserException();
        }
        if (user.getLogin().contains(" ") || Objects.equals(user.getLogin(), "")) {
            log.info("Wrong login {}", user.getLogin());
            throw new InvalidLoginException("login contains spaces");
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            user.setName(user.getLogin());
            log.info("Setting name to login {}", user.getLogin());
        }

        if (user.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant().isAfter(Instant.now())) {
            log.info("Wrong birthday date {}", user.getBirthday());
            throw new InvalidBirthdayException("Birthday in the future");
        }
    }

    private void checkIfUserIdPresent(int id) throws NotFoundException {
        String query = "SELECT * FROM users WHERE id = ?";
        jdbcTemplate.query(query, new UserMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("User not found")));
    }

}
