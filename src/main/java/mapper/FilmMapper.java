package mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FilmMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
        //LocalDate releaseDate = LocalDate.parse((rs.getString("release_date")));
        int duration = rs.getInt("duration");
        int rate = rs.getInt("rate");

        return Film.builder().id(id).name(name).description(description).releaseDate(releaseDate).duration(duration)
                .rate(rate).build();
    }
}

