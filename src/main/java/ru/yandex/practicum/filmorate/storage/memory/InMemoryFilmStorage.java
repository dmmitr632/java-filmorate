package ru.yandex.practicum.filmorate.storage.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidIdOfFilmException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.Constants.CINEMA_BIRTHDAY;
import static ru.yandex.practicum.filmorate.Constants.MAX_DESCRIPTION_LENGTH;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> films = new HashMap<>();
    private int id;

    @Override
    public List<Film> viewAllFilms() {
        log.info("All films, {}", films);
        return new ArrayList<>(films.values());
    }

    @Override
    public Map<Integer, Film> getFilms() {
        log.info("All films, {}", films);
        return films;
    }

    @Override
    public Film getFilmById(int filmId) {
        log.info("Film by id {}", filmId);
        if (filmId < 0) {
            throw new InvalidIdOfFilmException();
        }
        return this.getFilms().get(filmId);
    }

    @Override
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Trying to add Film with id {}, film.toString {} ", film.getId(), film);
        validateFilm(film);

        for (Film otherFilm : films.values()) {
            if (otherFilm.getId() == film.getId()) {
                throw new InvalidIdOfFilmException();
            }
        }
        if (film.getId() == 0) {
            film.setId(generateId());
        }
        films.put(film.getId(), film);
        log.info("Added Film with id {}, film.toString {} ", film.getId(), film);
        return film;
    }

    @Override
    public Film editFilm(@Valid @RequestBody Film film) {
        log.info("Trying to edit Film with id {}, film.toString {} ", film.getId(), film);
        validateFilm(film);
        for (Film filmEdited : films.values()) {
            if (filmEdited.getId() == film.getId()) {
                films.replace(film.getId(), film);
                log.info("Edited Film with id {}, film.toString {} ", film.getId(), film);
                return film;
            }
        }
        throw new InvalidIdOfFilmException();
    }

    @Override
    public void validateFilm(Film film) {

        if (film.getId() < 0) {
            throw new InvalidIdOfFilmException();
        }

        if (film.getName().equals("")) {
            log.info("Empty film name {}", film.getName());
            throw new InvalidNameException("film lacks name");
        }

        if (film.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            log.info("Too long film description, {} chars", film.getDescription().length());
            throw new InvalidDescriptionException("too long film description");
        }

        if (film.getReleaseDate().isBefore(CINEMA_BIRTHDAY)) {
            log.info("Wrong film date (before 1895-12-28) {}", film.getReleaseDate());
            throw new InvalidReleaseDateException("Film releaseDate before 1895-12-28");
        }
    }

    @Override
    public void deleteFilm(int id) {

    }

    public int generateId() {
        this.id++;
        return this.id;
    }
}
