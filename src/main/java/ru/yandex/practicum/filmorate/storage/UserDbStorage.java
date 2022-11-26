package ru.yandex.practicum.filmorate.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Qualifier("userDb")
public class UserDbStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> addUser(User user) {
        return null;
    }

    @Override
    public Optional<User> editUser(User user) {
        return null;
    }

    @Override
    public List<User> viewAllUsers() {
        return null;
    }

    @Override
    public Map<Integer, User> getUsers() {
        return null;
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return null;
    }

    @Override
    public void validateUser(User user) {

    }

}
