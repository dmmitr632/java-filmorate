package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("films")
public class FilmController {
    protected final FilmService filmService;
    protected final UserService userService;

    @Autowired
    public FilmController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @PutMapping
    public Film editFilm(@Valid @RequestBody Film film) {
        return filmService.editFilm(film);
    }

    @GetMapping
    public List<Film> viewAllFilms() {
        return filmService.viewAllFilms();
    }

    @GetMapping("/{id}")
    public Film viewFilmByID(@PathVariable int id) {
        return filmService.getFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void userAddsLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.addUserLikeOnFilm(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void userRemovesLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        filmService.deleteUserLikeOnFilm(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> viewMostLikedFilms(@RequestParam(defaultValue = "10") Integer count) {
        return filmService.viewMostLikedFilms(count);
    }
}
