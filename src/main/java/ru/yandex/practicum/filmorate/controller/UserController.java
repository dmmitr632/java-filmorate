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

    @PostMapping("/users")
    public User adduser(@RequestBody User user) throws InvalidLoginException, InvalidBirthdayException {
        log.info(user.toString());
        checkUser(user);
        for (User otherUser : users) {
            if (otherUser.getId() == user.getId()) {
                return null;
            }
        }
        users.add(user);
        return user;
    }

    @PutMapping(value = "/users")
    public User create(@RequestBody User user) throws InvalidLoginException, InvalidBirthdayException {
        log.info(user.toString());
        checkUser(user);
        for (User userEdited : users) {
            if (userEdited.getId() == user.getId()) {
                users.remove(userEdited);
                users.add(user);
                return user;
            }
        }
            return null;
    }

    @GetMapping(value = "/users")
    public List<User> viewAllUsers() {
        log.info(users.toString());
        return users;
    }

    public void checkUser(User user) throws InvalidLoginException, InvalidBirthdayException {

        if (user.getLogin().contains(" ")) {
            System.out.println("Wrong login");
            throw new InvalidLoginException("login contains spaces");
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            System.out.println("Setting email as login");
            user.setName(user.getLogin());
        }

        if (user.getBirthday().toInstant().isAfter(Instant.now())) {
            System.out.println("Wrong birthday date");
            throw new InvalidBirthdayException("Birthday in the future");
        }
    }
}
