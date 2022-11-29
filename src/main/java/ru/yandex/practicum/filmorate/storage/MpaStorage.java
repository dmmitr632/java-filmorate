package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

public interface MpaStorage {
    Mpa addMpa(Mpa mpa);
    Mpa editMpa(Mpa mpa);
    List<Mpa> getMpaRatings();

    Mpa getMpaById(int id);
}
