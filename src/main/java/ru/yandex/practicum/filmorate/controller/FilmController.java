package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import javax.validation.Valid;
import java.util.List;

//@Component
//@Slf4j
//@Validated
@RestController
@RequestMapping("films")
public class FilmController {
    final FilmService filmService;
    final InMemoryFilmStorage filmStorage;

    @Autowired
    public FilmController(FilmService filmService, InMemoryFilmStorage filmStorage) {
        this.filmService = filmService;
        this.filmStorage = filmStorage;
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        return filmStorage.addFilm(film);
    }

    @PutMapping
    public Film editFilm(@Valid @RequestBody Film film) {
        return filmStorage.editFilm(film);
    }

    @GetMapping
    public List<Film> viewAllFilms() {
        return filmStorage.viewAllFilms();
    }
}
