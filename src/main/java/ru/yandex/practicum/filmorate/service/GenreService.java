package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.util.List;

@Service
public class GenreService {
    private final GenreStorage genreStorage;

    @Autowired
    public GenreService(@Qualifier("genreDb") GenreStorage genreStorage) {
        this.genreStorage = genreStorage;
    }

    public Genre addGenre(Genre genre) {
        return genreStorage.addGenre(genre);
    }

    public Genre editGenre(Genre genre) {
        return genreStorage.editGenre(genre);
    }

    public List<Genre> viewAllGenres() {
        return genreStorage.getAllGenres();
    }

    public Genre getGenreById(int id) {
        return genreStorage.getGenreById(id);
    }
}
