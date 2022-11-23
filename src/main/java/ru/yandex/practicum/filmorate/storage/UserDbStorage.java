package ru.yandex.practicum.filmorate.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Qualifier("userDb")
public class UserDbStorage implements  UserStorage{


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
//        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from users where user_id = ?", userId);
//        if (userRows.next()) {
//
//            User user = new User(userRows.getI("user_id"), userRows.getString("username"),
//                    userRows.getString("nickname"));
//
//            log.info("Найден пользователь: {} {}", userRows.getString("user_id"), userRows.getString("nickname"));
//
//            user.setId(id);
//            return Optional.of(user);
//        } else {
//            log.info("Пользователь с идентификатором {} не найден.", id);
//            return Optional.empty();
//        }
//    }
        return null;
    }

    @Override
    public void validateUser(User user) {

    }

    @Override
    public int generateId() {
        return 0;
    }
}
