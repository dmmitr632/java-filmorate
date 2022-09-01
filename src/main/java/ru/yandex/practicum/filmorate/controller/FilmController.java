package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.film.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class FilmController {
    private final List<Film> films = new ArrayList<>();
    private int id;

    @PostMapping("/films")
    public Film addFilm(@Valid @RequestBody Film film)
            throws InvalidNameException, InvalidDurationException, InvalidDescriptionException,
            InvalidReleaseDateException {

        checkFilm(film);
        log.info(film.toString());
        for (Film otherFilm : films) {
            if (otherFilm.getId() == film.getId()) {
                return null;
            }
        }
        if (film.getId() == 0) {
            film.setId(generateId());
        }
        films.add(film);

        return film;
    }

    @PutMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film)
            throws InvalidNameException, InvalidDurationException, InvalidDescriptionException,
            InvalidReleaseDateException, InvalidIdOfEditedFilmException {
        checkFilm(film);
        for (Film filmEdited : films) {
            if (filmEdited.getId() == film.getId()) {
                films.remove(filmEdited);
                films.add(film);
                log.info(film.toString());
                return film;
            }
        }
        throw new InvalidIdOfEditedFilmException();
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

        LocalDate dateTime = LocalDate.of(1895, Month.DECEMBER, 28);

        if (film.getReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()
                .isBefore(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant())) {
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
