package mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = (rs.getInt("GENRE_ID"));
        String name = (rs.getString("NAME"));
        return Genre.builder().id(id).name(name).build();
    }
}
