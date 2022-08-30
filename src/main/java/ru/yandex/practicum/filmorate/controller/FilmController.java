package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidDurationException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import java.util.List;


@Slf4j
@RestController
public class FilmController {

    private final List<Film> films = new ArrayList<>();

    @PostMapping("/film/add")
    public Film addFilm(@Valid @RequestBody Film film)
            throws InvalidNameException, InvalidDurationException, InvalidDescriptionException,
            InvalidReleaseDateException {
        checkFilm(film);
        films.add(film);
        log.info(film.toString());
        return film;
    }

    @PutMapping(value = "/film/edit")
    public Film create(@Valid @RequestBody Film film)
            throws InvalidNameException, InvalidDurationException, InvalidDescriptionException,
            InvalidReleaseDateException {
        checkFilm(film);
        for (Film filmEdited: films) {
            if (filmEdited.getId() == film.getId())
                films.remove(filmEdited);

        }
        log.info(film.toString());
        films.add(film);
        return film;
    }

    @GetMapping(value = "/films")
    public List<Film> viewAllFilms() {
        log.info(films.toString());
        return films;
    }


    public void checkFilm(Film film)
            throws InvalidNameException, InvalidDescriptionException, InvalidReleaseDateException,
            InvalidDurationException {
        if (film.getName().equals("")) {
            throw new InvalidNameException();
        }

        if (film.getDescription().length() > 200) {
            throw new InvalidDescriptionException();
        }

        LocalDateTime dateTime = LocalDateTime.of(1895, Month.DECEMBER, 28, 0, 0);
        if (film.getReleaseDate().toInstant().isBefore(Instant.from(dateTime))) {
            throw new InvalidReleaseDateException();
        }

        if (film.getDuration().isNegative()) {
            throw new InvalidDurationException();
        }
    }

}
