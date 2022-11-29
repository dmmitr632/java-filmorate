//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.yandex.practicum.filmorate.FilmorateApplication;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
//import ru.yandex.practicum.filmorate.model.User;
//import ru.yandex.practicum.filmorate.service.UserService;
//import ru.yandex.practicum.filmorate.storage.memory_old.InMemoryUserStorage;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FilmorateApplication.class)
//class UserControllerTest extends UserController {
//    @Autowired
//    public UserControllerTest(UserService userService) {
//        super(userService);
//    }
//
//    @Test
//    public void addUserWithNegativeId() {
//        UserController userController = new UserController(this.userService);
//        User user = new User(-10, "aaa@gmail.com", "A", "", LocalDate.of(2000, 1, 1));
//
//        assertThrows(InvalidIdOfUserException.class, () -> userController.addUser(user));
//    }
//
//    @Test
//    public void addUserWithZeroId() {
//        UserController userController = new UserController(this.userService);
//        User user = new User(0, "aaa@gmail.com", "A", "Aa", LocalDate.of(2000, 1, 1));
//        userController.addUser(user);
//        assertEquals(1, user.getId());
//    }
//
//    @Test
//    public void addUserWithSpacesInLogin() {
//        UserController userController = new UserController(this.userService);
//        User user = new User(1, "aaa@gmail.com", "Aaa bbb", "", LocalDate.of(2000, 1, 1));
//
//        assertThrows(InvalidLoginException.class, () -> userController.addUser(user));
//    }
//
//    @Test
//    public void addUserWithoutNameAndWithCorrectLogin() {
//        UserController userController = new UserController(this.userService);
//        User user = new User(1, "aaa@gmail.com", "Aaabbb", "", LocalDate.of(2000, 1, 1));
//        userController.addUser(user);
//        assertEquals(user.getLogin(), user.getName());
//    }
//
//    @Test
//    public void addUserWithBirthdayInFuture() {
//        UserController userController = new UserController(this.userService);
//        User user = new User(1, "aaa@gmail.com", "Aaabbb", "Ccc", LocalDate.of(3000, 1, 1));
//
//        assertThrows(InvalidBirthdayException.class, () -> userController.addUser(user));
//    }
//}