package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

public interface FilmStorage {
    public Film addFilm(@Valid @RequestBody Film film);

    public Film editFilm(@Valid @RequestBody Film film);

    public List<Film> viewAllFilms();

    public void validateFilm(Film film);

    public int generateId();
}
