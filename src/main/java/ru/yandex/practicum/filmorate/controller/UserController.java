package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import javax.validation.Valid;
import java.util.List;

@Component
//@Slf4j
//@Validated
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final InMemoryUserStorage userStorage;

    @Autowired
    public UserController(UserService userService, InMemoryUserStorage userStorage) {
        this.userService = userService;
        this.userStorage = userStorage;
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        return userStorage.addUser(user);
    }

    @PutMapping
    public User editUser(@Valid @RequestBody User user) {
        return userStorage.editUser(user);
    }

    @GetMapping
    public List<User> viewAllUsers() {
        return userStorage.viewAllUsers();
    }
}
