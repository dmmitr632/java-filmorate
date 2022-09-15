package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Arrays;
import java.util.List;

//операции с фильмами, — добавление и удаление лайка, вывод 10 наиболее популярных фильмов по количеству лайков.

@Service
public class FilmService {
    public Film addLikeToFilm(Film film) {
        return film;
    }

    public Film removeLikeFromFilm(Film film) {
        return film;
    }

    public List<Film> viewTenMostLikedFilms(Film film) {
        return Arrays.asList(film);
    }
}




