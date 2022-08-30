package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmController {

    private final List<Film> films = new ArrayList<>();

    @PostMapping("/film/add")
    public Film addFilm(@RequestBody Film film) {
        films.add(film);
        return film;
    }

    @PutMapping(value = "/film/edit")
    public Film create(@RequestBody Film film) {
        for (Film filmEdited: films) {
            if (filmEdited.getId() == film.getId())
                films.remove(filmEdited);

        }
        films.add(film);
        return film;
    }

    @GetMapping(value = "/films")
    public List<Film> viewAllFilms() {
        return films;
    }

}
