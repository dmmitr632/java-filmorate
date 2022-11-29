package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;

import java.util.List;

@Component
@Qualifier("friendsDb")
public class UserFriendsDbStorage implements UserFriendStorage {
    private final JdbcTemplate jdbcTemplate;

    public UserFriendsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUserFriends(int id) {
        String query = "SELECT * FROM users WHERE id IN (SELECT friend_id FROM users_friends WHERE id = ?)";
        return jdbcTemplate.query(query, new UserMapper(), id);
    }

    @Override
    public List<User> getCommonFriends(int id, int friendId) {
        String query = "SELECT *FROM users WHERE id IN(SELECT * FROM (SELECT friend_id FROM (" +
                "SELECT friend_id FROM users_friends WHERE id = ?) WHERE friend_id IN (" +
                "SELECT friend_id FROM users_friends WHERE id = ?)))";
        return jdbcTemplate.query(query, new UserMapper(), id, friendId);
    }

    @Override
    public void addFriend(int id, int friendId) {
        String query = "INSERT INTO users_friends VALUES(?, ?)";
        jdbcTemplate.update(query, id, friendId);
    }

    @Override
    public void deleteFriend(int id, int friendId) {
        String query = "DELETE FROM users_friends WHERE id = ? AND friend_id = ?";
        jdbcTemplate.update(query, id, friendId);
    }
}
