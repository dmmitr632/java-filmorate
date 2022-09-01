package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ValidationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FilmorateApplicationTests extends UserController {
    @BeforeAll
    public static void beforeStart() {
        System.out.println("@BeforeAll");
        SpringApplication.run(FilmorateApplication.class);
    }

    @Test
    public void putFilmWithEmptyName() {
        Film film = new Film(1, "", "description 1", LocalDate.of(2000, 1, 1), 100);
        assertThrows(ValidationException.class, () -> new FilmController().addFilm(film));
    }

    //    @Test
    //    public void putUserWithEmptyName() {
    //        Film film = new Film(1, "", "description 1", LocalDate.of(2000, 1, 1), 100);
    //        assertThrows(ValidationException.class,() -> new FilmController().addFilm(film));
    //    }
}
