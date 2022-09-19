//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.yandex.practicum.filmorate.FilmorateApplication;
//import ru.yandex.practicum.filmorate.exceptions.film.InvalidDescriptionException;
//import ru.yandex.practicum.filmorate.exceptions.film.InvalidReleaseDateException;
//import ru.yandex.practicum.filmorate.model.Film;
//import ru.yandex.practicum.filmorate.service.FilmService;
//import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
//import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
//// import ru.yandex.practicum.filmorate.storage.UserStorage;
//
//import javax.validation.ValidationException;
//import java.time.LocalDate;
//import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FilmorateApplication.class)
//
//class FilmControllerTest extends FilmController {
//    @Autowired
//    public FilmControllerTest(FilmService filmService, InMemoryUserStorage userStorage,
//                              InMemoryFilmStorage filmStorage) {
//        super(filmService, userStorage, filmStorage);
//    }
//
//    @Test
//    public void addFilmWithEmptyName() {
//        FilmController filmController = new FilmControllerTest(this.filmService, (InMemoryUserStorage) this.userStorage,
//                (InMemoryFilmStorage) this.filmStorage);
//        Film film = new Film(1, "", "description 1", LocalDate.of(2000, 1, 1), 60);
//
//        assertThrows(ValidationException.class, () -> filmController.addFilm(film));
//    }
//
//    @Test
//    public void addFilmWithTooLongDescription() {
//        FilmController filmController = new FilmControllerTest(this.filmService,
//                (InMemoryUserStorage) this.userStorage, (InMemoryFilmStorage) this.filmStorage);
//
//        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.,-()[]/! ";
//        StringBuilder randomLongDescription = new StringBuilder();
//        Random random = new Random();
//        int randomNum = ThreadLocalRandom.current().nextInt(201, 1001);
//        for (int i = 0; i < randomNum; i++) {
//            randomLongDescription.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
//        }
//
//        Film film = new Film(1, "film 1", randomLongDescription.toString(), LocalDate.of(2000, 1, 1), 60);
//
//        assertThrows(InvalidDescriptionException.class, () -> filmController.addFilm(film));
//    }
//
//    @Test
//    public void addFilmWithTooEarlyReleaseDate() {
//        FilmController filmController = new FilmControllerTest(this.filmService, (InMemoryUserStorage) this.userStorage,
//                (InMemoryFilmStorage) this.filmStorage);
//        Film film = new Film(1, "film1", "description 1", LocalDate.of(1500, 1, 1), 60);
//
//        assertThrows(InvalidReleaseDateException.class, () -> filmController.addFilm(film));
//    }
//}