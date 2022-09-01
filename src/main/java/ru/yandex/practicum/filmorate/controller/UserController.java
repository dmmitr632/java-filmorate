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
import java.util.*;

@Slf4j
@Validated
@RestController
@RequestMapping("users")
public class UserController {
    private final Map<Integer,User> users = new HashMap<>();
    private int id;

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        log.info("Trying to add User with id {}, user.toString {} ", user.getId(), user);
        validateUser(user);
        for (User otherUser : users.values()) {
            if (otherUser.getId() == user.getId()) {
                throw new InvalidIdOfUserException();
            }
        }
        if (user.getId() == 0) {
            user.setId(generateId());
        }
        users.put(user.getId(), user);
        log.info("Added User with id {}, user.toString {} ", user.getId(), user);
        return user;
    }

    @PutMapping
    public User editUser(@Valid @RequestBody User user) {
        log.info("Trying to edit User with id {}, user.toString {} ", user.getId(), user);
        validateUser(user);
        for (User userEdited : users.values()) {
            if (userEdited.getId() == user.getId()) {
                users.replace(userEdited.getId(), user);
                log.info("Edited User with id {}, user.toString {} ", user.getId(), user);
                return user;
            }
        }
        throw new InvalidIdOfUserException();
    }

    @GetMapping
    public List<User> viewAllUsers() {
        log.info("All users {} ", users);
        return new ArrayList<>(users.values());
    }

    public void validateUser(User user) {
        if (user.getId() < 0) {
            throw new InvalidIdOfUserException();
        }
        if (user.getLogin().contains(" ") || Objects.equals(user.getLogin(), "")) {
            log.info("Wrong login {}", user.getLogin());
            throw new InvalidLoginException("login contains spaces");
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            user.setName(user.getLogin());
            log.info("Setting name to login {}", user.getLogin());
        }

        if (user.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant().isAfter(Instant.now())) {
            log.info("Wrong birthday date {}", user.getBirthday());
            throw new InvalidBirthdayException("Birthday in the future");
        }
    }

    public int generateId() {
        this.id++;
        return this.id;
    }
}
