package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exceptions.film.InvalidNameException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

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
    public void addFilmWithEmptyName() {
        Film film = new Film(1, "", "description 1", LocalDate.of(2000, 1, 1), 60);
        assertThrows(InvalidNameException.class, () -> new FilmController().addFilm(film));
    }

    @Test
    public void addUserWithWrongId() {
        User user = new User(-10, "aaa@gmail.com", "A", "", LocalDate.of(2000, 1, 1));
        assertThrows(InvalidIdOfUserException.class, () -> this.addUser(user));
    }
}
