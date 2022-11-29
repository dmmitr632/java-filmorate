package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.List;

@Service
public class MpaService {

    private final MpaStorage mpaStorage;

    @Autowired
    public MpaService(@Qualifier("mpaDb") MpaStorage mpaStorage) {
        this.mpaStorage = mpaStorage;
    }


    public Mpa addMpa(Mpa mpa) {
        return mpaStorage.addMpa(mpa);
    }

    public Mpa editMpa(Mpa mpa) {
        return mpaStorage.editMpa(mpa);
    }

    public List<Mpa> viewAllMpas() {
        return mpaStorage.getMpaRatings();
    }
}
