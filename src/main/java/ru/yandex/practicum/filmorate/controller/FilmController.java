package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.film.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("films")
public class FilmController {

    private static final LocalDate CINEMA_BIRTHDAY = LocalDate.of(1895, Month.DECEMBER, 28);
    private final Map<Integer,Film> films = new HashMap<>();
    private int id;

    @PostMapping
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

    @PutMapping
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

    @GetMapping
    public List<Film> viewAllFilms() {
        log.info("All films, {}", films);
        return (List<Film>) films.values();
    }

    public void validateFilm(Film film) {
        if (film.getName().equals("")) {
            throw new InvalidNameException();
        }

        if (film.getDescription().length() > 200) {
            throw new InvalidDescriptionException();
        }



        if (film.getReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()
                .isBefore(CINEMA_BIRTHDAY.atStartOfDay(ZoneId.systemDefault()).toInstant())) {
            throw new InvalidReleaseDateException();
        }

        if (film.getDuration() < 0) {
            throw new InvalidDurationException();
        }
    }

    public int generateId() {
        this.id++;
        return this.id;
    }
}
