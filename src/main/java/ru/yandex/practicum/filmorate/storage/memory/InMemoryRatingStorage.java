package ru.yandex.practicum.filmorate.storage.memory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.FilmMpaRating;
import ru.yandex.practicum.filmorate.storage.RatingStorage;

import java.util.List;

@Component
@Qualifier("InMemoryRatings")
public class InMemoryRatingStorage implements RatingStorage {
    //TODO: заполнить
    @Override
    public List<FilmMpaRating> getMpaRatings() {
        return null;
    }

    @Override
    public FilmMpaRating getRatingById(int id) {
        return null;
    }
}
