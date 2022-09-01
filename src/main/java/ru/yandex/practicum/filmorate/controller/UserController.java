package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Validated
@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();
    private int id;

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        log.info(user.toString());
        checkUser(user);
        for (User otherUser : users) {
            if (otherUser.getId() == user.getId()) {
                throw new InvalidIdOfUserException();
            }
        }
        if (user.getId() == 0) {
            user.setId(generateId());
        }
        users.add(user);
        return user;
    }

    @PutMapping(value = "/users")
    public User editUser(@Valid @RequestBody User user) {
        log.info(user.toString());
        checkUser(user);
        for (User userEdited : users) {
            if (userEdited.getId() == user.getId()) {
                users.remove(userEdited);
                users.add(user);
                log.info(user.toString());
                return user;
            }
        }
        throw new InvalidIdOfUserException();
    }

    @GetMapping(value = "/users")
    public List<User> viewAllUsers() {
        log.info(users.toString());
        return users;
    }

    public void checkUser(User user) {
        if (user.getId() < 0) {
            throw new InvalidIdOfUserException();
        }
        if (user.getLogin().contains(" ") || Objects.equals(user.getLogin(), "")) {
            System.out.println("Wrong login");
            throw new InvalidLoginException("login contains spaces");
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            System.out.println("Setting login as name");
            user.setName(user.getLogin());
        }

        if (user.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant().isAfter(Instant.now())) {
            System.out.println("Wrong birthday date");
            throw new InvalidBirthdayException("Birthday in the future");
        }
    }

    public int generateId() {
        this.id++;
        return this.id;
    }
}
