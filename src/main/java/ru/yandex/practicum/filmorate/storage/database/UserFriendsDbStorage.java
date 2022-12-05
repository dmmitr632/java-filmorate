package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
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
        validateIfUserInDb(id);

        String query = "SELECT * FROM users, users_friends WHERE users.id = users_friends.friend_id AND users_friends" +
                ".id = ?";
        return jdbcTemplate.query(query, new UserMapper(), id);
    }

    @Override
    public List<User> getCommonFriends(int id, int friendId) {
        validateIfUserInDb(id);
        validateIfUserInDb(friendId);


        String query =
                "SELECT * FROM users u, users_friends uf1, users_friends uf2 WHERE u.id = uf1.friend_id AND u" +
                        ".id = uf2.friend_id AND uf1.id = ? AND uf2.id = ?";

        return jdbcTemplate.query(query, new UserMapper(), id, friendId);
    }

    @Override
    public void addFriend(int id, int friendId) {
        validateIfUserInDb(id);
        validateIfUserInDb(friendId);
        String query = "INSERT INTO users_friends VALUES(?, ?)";
        jdbcTemplate.update(query, id, friendId);
    }

    @Override
    public void deleteFriend(int id, int friendId) {
        validateIfUserInDb(id);
        validateIfUserInDb(friendId);
        String query = "DELETE FROM users_friends WHERE id = ? AND friend_id = ?";
        jdbcTemplate.update(query, id, friendId);
    }

    private void validateIfUserInDb(int id) throws NotFoundException {
        String query = "SELECT * FROM users WHERE id = ?";
        jdbcTemplate.query(query, new UserMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("User not found")));
    }
}
