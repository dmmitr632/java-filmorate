package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("users.id");
        String email = rs.getString("users.email");
        String login = rs.getString("users.login");
        String name = rs.getString("users.name");
        LocalDate birthday = rs.getDate("users.birthday").toLocalDate();

        return User.builder().id(id).email(email).login(login).name(name).birthday(birthday).build();
    }
}
