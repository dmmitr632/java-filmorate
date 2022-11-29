package ru.yandex.practicum.filmorate.storage.memory_old;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.List;

@Component
@Qualifier("InMemoryRatings")
public class InMemoryRatingStorage implements MpaStorage {
    @Override
    public Mpa addMpa(Mpa mpa) {
        return null;
    }

    @Override
    public Mpa editMpa(Mpa mpa) {
        return null;
    }

    //TODO: заполнить
    @Override
    public List<Mpa> getMpaRatings() {
        return null;
    }

    @Override
    public Mpa getMpaById(int id) {
        return null;
    }
}
