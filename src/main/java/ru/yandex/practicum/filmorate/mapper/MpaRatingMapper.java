package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaRatingMapper implements RowMapper<FilmMpaRating> {
    @Override
    public FilmMpaRating mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("mpa_rating_id");
        String name = rs.getString("rating_name");
        return FilmMpaRating.builder().id(id).name(name).build();
    }
}
