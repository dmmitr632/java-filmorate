package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.ArrayList;

public interface GenreStorage {
    ArrayList<Genre> getAllGenres();

    Genre getGenreById(int id);
}


