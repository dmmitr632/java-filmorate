package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikesReversedComparator;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.List;

//операции с фильмами, — добавление и удаление лайка, вывод 10 наиболее популярных фильмов по количеству лайков.

@Service
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public List<Film> viewMostLikedFilms(int count) {
        List<Film> mostLikedFilms = new ArrayList<>();
        List<Film> allFilms = filmStorage.viewAllFilms();

        int sizeOfFilmList = allFilms.size();
        FilmLikesReversedComparator filmLikesReversedComparator = new FilmLikesReversedComparator();
        allFilms.sort(filmLikesReversedComparator);

        for (int i = 0; i < Math.min(count, sizeOfFilmList); i++) {

            mostLikedFilms.add(allFilms.get(i));
        }

        return mostLikedFilms;
    }
}




