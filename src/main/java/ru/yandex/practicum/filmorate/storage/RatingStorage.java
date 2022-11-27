package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.FilmMpaRating;

import java.util.List;

public interface RatingStorage {
    List<FilmMpaRating> getMpaRatings();

    FilmMpaRating getRatingById(int id);
}
