package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import javax.validation.Valid;
import java.util.List;

@Component
//@Slf4j
@Validated
@RestController
@RequestMapping("films")
public class FilmController {
    final FilmService filmService;
    final InMemoryUserStorage userStorage;
    final InMemoryFilmStorage filmStorage;

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

    //PUT /films/{id}/like/{userId} — пользователь ставит лайк фильму.
    //DELETE /films/{id}/like/{userId} — пользователь удаляет лайк.
    //GET /films/popular?count={count} — возвращает список из первых count фильмов по количеству лайков. Если
    // значение параметра count не задано, верните первые 10.
    //Убедитесь, что ваше приложение возвращает корректные HTTP-коды.
    //400 — если ошибка валидации: ValidationException;
    //404 — для всех ситуаций, если искомый объект не найден;
    //500 — если возникло исключение.

    @PutMapping("/{id}/like/{userId}")
    public void userAddsLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        System.out.println("FilmController userAddsLikeToFilm");
        filmStorage.getFilmById(id).addUsersWhoLikedFilm(userId);
        userStorage.getUserById(userId).addLikedFilmId(id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void userRemovesLikeToFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        userStorage.getUserById(userId).removeLikedFilmId(id);
        filmStorage.getFilmById(id).removeUsersWhoLikedFilm(userId);
    }

    @GetMapping("/popular")
    public List<Film> viewTenMostLikedFilms() {
        return filmService.viewMostLikedFilms(10);
    }

    @GetMapping("/popular?count={number}")
    public List<Film> viewMostLikedFilms(@PathVariable Integer number) {
        return filmService.viewMostLikedFilms(number);
    }
}
