package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("genres")
public class GenreController {
    protected final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public Genre addGenre(@Valid @RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @PutMapping
    public Genre editGenre(@Valid @RequestBody Genre genre) {
        return genreService.editGenre(genre);
    }

    @GetMapping
    public List<Genre> viewAllGenres() {
        return genreService.viewAllGenres();
    }

}
