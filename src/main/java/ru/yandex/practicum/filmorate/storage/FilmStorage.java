package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

public interface FilmStorage {
    Film addFilm(@Valid @RequestBody Film film);

    Film editFilm(@Valid @RequestBody Film film);

    List<Film> viewAllFilms();

    Film viewFilm(@Valid @RequestBody Film film);

    void validateFilm(Film film);

    int generateId();
}
