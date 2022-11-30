package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmLikesComparator;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.LikesOnFilmsStorage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final LikesOnFilmsStorage likesOnFilmsStorage;

    @Autowired
    public FilmService(@Qualifier("filmDb") FilmStorage filmStorage,
                       @Qualifier("likesDb") LikesOnFilmsStorage likesOnFilmsStorage) {
        this.filmStorage = filmStorage;
        this.likesOnFilmsStorage = likesOnFilmsStorage;
    }

    public List<Film> viewMostLikedFilms(int count) {

        return filmStorage.viewAllFilms().stream().sorted(new FilmLikesComparator()).limit(count)
                .collect(Collectors.toList());
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


    public void addUserLikeOnFilm(Integer filmId, Integer userId) {
        likesOnFilmsStorage.addLike(filmId, userId);
    }

    public void deleteUserLikeOnFilm(Integer filmId, Integer userId) {
        likesOnFilmsStorage.deleteLike(filmId, userId);
    }
}




