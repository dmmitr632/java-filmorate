package ru.yandex.practicum.filmorate.storage.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.MpaMapper;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Qualifier("mpaDb")
public class MpaDbStorage implements MpaStorage {
    private JdbcTemplate jdbcTemplate;

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mpa getMpaById(int id) {
        String query = "SELECT * FROM mpa WHERE id = ?";
        return jdbcTemplate.query(query, new MpaMapper(), id).stream().findAny()
                .orElseThrow(() -> new NotFoundException("Рейтинга с" + id + " не существует"));
    }

    @Override
    public Mpa addMpa(Mpa mpa) {
        return null;
    }

    @Override
    public Mpa editMpa(Mpa mpa) {
        return null;
    }

    @Override
    public List<Mpa> getMpaRatings() {
        String query = "SELECT * FROM mpa";
        List<Mpa> ratings = new ArrayList<>(jdbcTemplate.query(query, new MpaMapper()));
        return ratings;
    }
}
