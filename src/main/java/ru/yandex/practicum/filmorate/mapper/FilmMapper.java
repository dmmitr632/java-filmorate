package ru.yandex.practicum.filmorate.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FilmMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("films.id");
        String name = rs.getString("films.name");
        String description = rs.getString("films.description");
        LocalDate releaseDate = rs.getDate("films.release_date").toLocalDate();
        Integer duration = rs.getInt("films.duration");
        Integer rate = rs.getInt("films.rate");
        return Film.builder().id(id).name(name).description(description).releaseDate(releaseDate).duration(duration)
                .rate(rate).build();
    }
}

