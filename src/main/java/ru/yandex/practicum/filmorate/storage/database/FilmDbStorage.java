package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.yandex.practicum.filmorate.Constants.CINEMA_BIRTHDAY;
import static ru.yandex.practicum.filmorate.Constants.MAX_DESCRIPTION_LENGTH;

@Slf4j
@Component
@Qualifier("filmDb")
public class FilmDbStorage implements FilmStorage {
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Film addFilm(Film film) throws ValidationException {

        validateFilm(film);

        String query =
                "INSERT INTO films (name, description, release_date, duration, mpa_id)" + " VALUES(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(query, new String[]{"id"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getMpaId());
            return stmt;
        }, keyHolder);

        film.setId(keyHolder.getKey().intValue());

        this.addFilmGenre(film);

        return film;
    }

    @Override
    public Film editFilm(Film film) throws ValidationException, NotFoundException {
        validateFilm(film);

        String query = "MERGE INTO films(id, name, description, release_date, duration, rate, mpa_id)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";

        validateIfFilmInDb(film.getId());

        jdbcTemplate.update(query, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getDuration(), film.getRate(), film.getMpaId());

        this.editFilmGenre(film);
        return film;
    }

    public List<Film> viewAllFilms() {

        String queryMpa = "SELECT * FROM mpa, films WHERE films.mpa_id = mpa.id";

        HashMap<Integer, Film> films = new HashMap<>();

        jdbcTemplate.query(queryMpa, (ResultSetExtractor<Map<Integer, Film>>) rs -> {
            while (rs.next()) {

                Integer id = (rs.getInt("films.id"));
                String name = rs.getString("films.name");
                String description = rs.getString("description");
                LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
                Integer duration = rs.getInt("duration");
                Integer rate = rs.getInt("rate");

                Mpa mpaRating = Mpa.builder().id(rs.getInt("mpa.id")).name(rs.getString("mpa.name")).build();

                Film film = Film.builder().id(id).name(name).description(description).releaseDate(releaseDate)
                        .duration(duration).rate(rate).mpa(mpaRating).genres(new ArrayList<>(Collections.emptyList()))
                        .build();

                films.put(id, film);
            }
            return films;
        });

        String queryGenres = "SELECT DISTINCT fg.id AS fg_id, g.id AS g_id ,g.name AS g_name FROM films_genres AS fg" +
                " LEFT JOIN genres AS g ON g.id = fg.genre_id";
        jdbcTemplate.query(queryGenres, (ResultSetExtractor<Map<Integer, Film>>) (ResultSet rs) -> {
            while (rs.next()) {
                Film film = films.get(rs.getInt("fg_id"));

                if ((rs.getObject("g_id") != null)) {
                    Genre genre = Genre.builder().id(rs.getInt("g_id")).name(rs.getString("g_name")).build();

                    film.getGenres().add(genre);
                }
            }
            return films;
        });
        Collection<Film> filmsCollection = films.values();
        return new ArrayList<>(filmsCollection);
    }

    private ArrayList<Genre> getGenreForFilmByFilmId(int filmId) {

        String query = "SELECT * FROM genres g, films_genres fg WHERE fg.genre_id = g.id AND fg.id=?";
        return (ArrayList<Genre>) jdbcTemplate.query(query, new GenreMapper(), filmId);
    }

    private Mpa getRating(int id) {
        validateIfFilmInDb(id);

        String query = "SELECT * FROM mpa, films WHERE films.mpa_id = mpa.id AND films.id=?";

        return jdbcTemplate.query(query, new MpaMapper(), id).stream().findAny()
                .orElseThrow(() -> new ValidationException("???????????????? ?????????????? ?????? id" + id));
    }

    @Override
    public Map<Integer, Film> getFilms() {
        List<Film> films = this.viewAllFilms();
        Map<Integer, Film> filmsMap = new HashMap<>();
        for (Film film : films) {
            filmsMap.put(film.getId(), film);
        }
        return filmsMap;
    }

    @Override
    public Film getFilmById(int filmId) {
        Film film = validateIfFilmInDb(filmId);
        Mpa rating = this.getRating(film.getId());
        film.setMpa(rating);
        ArrayList<Genre> genres = this.getGenreForFilmByFilmId(film.getId());
        film.setGenres(genres);
        return film;
    }

    @Override
    public void validateFilm(Film film) {
        if (film.getId() != null) {
            if (film.getId() < 0) {
                throw new ValidationException("id < 0");
            }
        }

        if (film.getName().equals("")) {
            log.info("Empty film name {}", film.getName());
            throw new ValidationException("film lacks name");
        }

        if (film.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            log.info("Too long film description, {} chars", film.getDescription().length());
            throw new ValidationException("too long film description");
        }

        if (film.getReleaseDate().isBefore(CINEMA_BIRTHDAY)) {
            log.info("Wrong film date (before 1895-12-28) {}", film.getReleaseDate());
            throw new ValidationException("Film releaseDate before 1895-12-28");
        }

        if (film.getDuration() < 0) {
            throw new ValidationException("Duration of film is less than zero");
        }
        if (film.getMpa() == null) {
            throw new ValidationException("Film lacks Mpa rating");
        }
    }

    private Film validateIfFilmInDb(int id) throws NotFoundException {
        String query = "SELECT * FROM films WHERE id = ?";
        return jdbcTemplate.query(query, new FilmMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException(("Film not found")));
    }

    private void addFilmGenre(Film film) {
        String queryAddGenreFilm = "INSERT INTO films_genres(id, genre_id) VALUES (?, ?)";
        if (film.getGenres() != null) {
            List<Genre> genres = new ArrayList<>(film.getGenres());
            List<Genre> genresWithoutDuplicates = genres.stream().distinct().collect(Collectors.toList());
            film.setGenres(genresWithoutDuplicates);
            for (Genre genre : genresWithoutDuplicates) {
                jdbcTemplate.update(queryAddGenreFilm, film.getId(), genre.getId());
            }
        }
    }

    private void editFilmGenre(Film film) {

        String queryDeleteGenres = "DELETE FROM films_genres WHERE id = ?";
        jdbcTemplate.update(queryDeleteGenres, film.getId());

        String queryAddGenreFilm = "INSERT INTO films_genres(id, genre_id) VALUES (?, ?)";
        if (film.getGenres() != null) {
            List<Genre> updatedGenres = new ArrayList<>(film.getGenres());
            List<Genre> updatedGenresWithoutDuplicates = updatedGenres.stream().distinct().collect(Collectors.toList());
            film.setGenres(updatedGenresWithoutDuplicates);
            for (Genre genre : updatedGenresWithoutDuplicates) {
                jdbcTemplate.update(queryAddGenreFilm, film.getId(), genre.getId());
            }
        } else {
            jdbcTemplate.update(queryAddGenreFilm, film.getId(), null);
        }
    }
}
