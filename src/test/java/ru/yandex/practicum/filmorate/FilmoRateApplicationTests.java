package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.database.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.database.UserDbStorage;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmoRateApplicationTests {
    private final UserDbStorage userDbStorage;
    private final FilmDbStorage filmDbStorage;

    @Test
    public void testFindUserById() {

        User user = userDbStorage.getUserById(1);

        assertThat(user).hasFieldOrPropertyWithValue("id", 1);
    }

    @Test
    public void addFilmWithEmptyName() {

        Film film = Film.builder().id(1).name("").description("1234").releaseDate(LocalDate.of(2000, 1, 1)).duration(60)
                .rate(8).build();
        assertThrows(ValidationException.class, () -> filmDbStorage.addFilm(film));
    }

    @Test
    public void addFilmWithTooLongDescription() {

        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.,-()[]/! ";
        StringBuilder randomLongDescription = new StringBuilder();
        Random random = new Random();
        int randomNum = ThreadLocalRandom.current().nextInt(201, 1001);
        for (int i = 0; i < randomNum; i++) {
            randomLongDescription.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        Film film = Film.builder().id(1).name("film 1").description(randomLongDescription.toString())
                .releaseDate(LocalDate.of(2000, 1, 1)).duration(60).rate(8).build();

        assertThrows(ValidationException.class, () -> filmDbStorage.addFilm(film));
    }

    @Test
    public void addFilmWithTooEarlyReleaseDate() {

        Film film = Film.builder().id(1).name("film 1").description("1234").releaseDate(LocalDate.of(1800, 1, 1))
                .duration(60).rate(8).build();
        assertThrows(ValidationException.class, () -> filmDbStorage.addFilm(film));
    }

    @Test
    public void addUserWithNegativeId() {
        User user = User.builder().id(-10).email("aaa@gmail.com").login("Aaabbb").name("")
                .birthday(LocalDate.of(2000, 1, 1)).build();

        assertThrows(ru.yandex.practicum.filmorate.exceptions.ValidationException.class,
                () -> userDbStorage.addUser(user));
    }

    @Test
    public void addUserWithSpacesInLogin() {
        User user =
                User.builder().id(1).email("aaa@gmail.com").login("Aaa bbb").name("").birthday(LocalDate.of(2000, 1, 1))
                        .build();
        assertThrows(ru.yandex.practicum.filmorate.exceptions.ValidationException.class,
                () -> userDbStorage.addUser(user));
    }

    @Test
    public void addUserWithoutNameAndWithCorrectLogin() {
        User user =
                User.builder().id(1).email("aaa@gmail.com").login("Aaabbb").name("").birthday(LocalDate.of(2000, 1, 1))
                        .build();
        userDbStorage.addUser(user);
        assertEquals(user.getLogin(), user.getName());
    }

    @Test
    public void addUserWithBirthdayInFuture() {
        User user =
                User.builder().id(1).email("aaa@gmail.com").login("Aaa bbb").name("").birthday(LocalDate.of(2040, 1, 1))
                        .build();

        assertThrows(ru.yandex.practicum.filmorate.exceptions.ValidationException.class,
                () -> userDbStorage.addUser(user));
    }
}
