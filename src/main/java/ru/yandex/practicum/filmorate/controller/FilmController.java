package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("films")
public class FilmController {
    protected final FilmService filmService;
    protected final UserStorage userStorage;
    protected final FilmStorage filmStorage;

    @Autowired
    public FilmController(FilmService filmService, InMemoryUserStorage userStorage, InMemoryFilmStorage filmStorage) {
        this.filmService = filmService;
        this.userStorage = userStorage;
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

    @GetMapping("/{id}")
    public Film viewFilmByID(@PathVariable int id) {
        return filmStorage.getFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void userAddsLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        filmStorage.getFilmById(id).addUsersWhoLikedFilm(userId);
        userStorage.getUserById(userId).addLikedFilmId(id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void userRemovesLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        userStorage.getUserById(userId).removeLikedFilmId(id);
        filmStorage.getFilmById(id).removeUsersWhoLikedFilm(userId);
    }

    @GetMapping("/popular")
    public List<Film> viewMostLikedFilms(@RequestParam(defaultValue = "10") Integer count) {
        return filmService.viewMostLikedFilms(count);
    }
}
