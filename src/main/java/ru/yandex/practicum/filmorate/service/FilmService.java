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

    //    public Film addLikeToFilm(Film film, User user) {
    //        film.addUsersWhoLikedFilm(user.getId());
    //        user.addLikedFilmId(film.getId());
    //        return film;
    //    }
    //
    //    public Film removeLikeFromFilm(Film film, User user) {
    //        film.removeUsersWhoLikedFilm(user.getId());
    //        user.removeLikedFilmId(film.getId());
    //        return film;
    //    }

    public List<Film> viewMostLikedFilms(int number) {
        List<Film> mostLikedFilms = new ArrayList<>();
        List<Film> allFilms = filmStorage.viewAllFilms();
        int sizeOfFilmList = allFilms.size();
        FilmLikesReversedComparator filmLikesReversedComparator = new FilmLikesReversedComparator();
        allFilms.sort(filmLikesReversedComparator);

        for (int i = 0; i < Math.max(number, sizeOfFilmList); i++) {
            mostLikedFilms.add(allFilms.get(i));
        }
        return mostLikedFilms;
    }
}




