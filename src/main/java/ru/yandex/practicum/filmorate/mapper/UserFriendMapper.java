package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.UserFriend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFriendMapper implements RowMapper<UserFriend> {
    @Override
    public UserFriend mapRow(ResultSet rs, int rowNum) throws SQLException {
        int userId = rs.getInt("id");
        int friendId = rs.getInt("friend_id");
        return UserFriend.builder().id(userId).friendId(friendId).build();
    }
}
