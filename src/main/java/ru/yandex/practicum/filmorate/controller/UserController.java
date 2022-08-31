package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user/add")
    public User adduser(@RequestBody User user) throws InvalidLoginException, InvalidBirthdayException {
        log.info(user.toString());
        checkUser(user);
        users.add(user);
        return user;
    }

    @PutMapping(value = "/user/edit")
    public User create(@RequestBody User user) {
        for (User userEdited : users) {
            if (userEdited.getId() == user.getId()) {
                users.remove(userEdited);
            }
        }
        log.info(user.toString());
        users.add(user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> viewAllUsers() {
        log.info(users.toString());
        return users;
    }

    public void checkUser(User user) throws InvalidLoginException, InvalidBirthdayException {

        if (user.getLogin().contains(" ")) {
            throw new InvalidLoginException();
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            user.setName(user.getLogin());
        }

        if (user.getBirthday().toInstant().isAfter(Instant.now())) {
            throw new InvalidBirthdayException();
        }
    }
}
