package mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaRatingMapper implements RowMapper<FilmMpaRating> {
    @Override
    public FilmMpaRating mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("RATING_ID");
        String name = rs.getString("NAME");
        return FilmMpaRating.builder().id(id).name(name).build();
    }
}
