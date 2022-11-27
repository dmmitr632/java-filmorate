package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface FilmStorage {
    Film addFilm(@Valid @RequestBody Film film);

    Film editFilm(@Valid @RequestBody Film film);

    List<Film> viewAllFilms();

    Map<Integer, Film> getFilms();

    Film getFilmById(@Valid int filmId);

    void validateFilm(Film film);

    void deleteFilm(int id);

    //int generateId();
}
