package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikesReversedComparator;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film editFilm(Film film) {
        return filmStorage.editFilm(film);
    }

    public Film getFilmById(int filmId) {
        return filmStorage.getFilmById(filmId);
    }

    public Map<Integer, Film> getFilms() {
        return filmStorage.getFilms();
    }

    public List<Film> viewAllFilms() {
        return filmStorage.viewAllFilms();
    }
}




