//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.yandex.practicum.filmorate.FilmorateApplication;
//import ru.yandex.practicum.filmorate.controller.UserController;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
//import ru.yandex.practicum.filmorate.model.User;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserValidationTest extends UserController {
//    @BeforeAll
//    public static void beforeStart() {
//        System.out.println("@BeforeAll");
//        SpringApplication.run(FilmorateApplication.class);
//    }
//
//    @Test
//    public void addUserWithWrongId() {
//        User user = new User(-10, "aaa@gmail.com", "A", "", LocalDate.of(2000, 1, 1));
//        assertThrows(InvalidIdOfUserException.class, () -> new UserController().addUser(user));
//    }
//}