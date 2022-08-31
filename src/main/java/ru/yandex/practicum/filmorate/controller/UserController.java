package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private int id;

    @PostMapping("/users")
    public User adduser(@RequestBody User user) throws InvalidLoginException, InvalidBirthdayException {
        log.info(user.toString());
        checkUser(user);
        for (Integer otherUserId : users.keySet()) {
            if (otherUserId == user.getId()) {
                return null;
            }
        }
        if (user.getId() == 0) {
            user.setId(generateId());
        }
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping(value = "/users")
    public User create(@RequestBody User user) throws InvalidLoginException, InvalidBirthdayException {
        log.info(user.toString());
        checkUser(user);
        for (Integer userEditedId : users.keySet()) {
            if (users.get(userEditedId).getId() == user.getId()) {
                users.replace(userEditedId, user);
                return user;
            }
        }
        return null;
    }

    @GetMapping(value = "/users")
    public List<User> viewAllUsers() {
        log.info(users.toString());
        return (List<User>) users.values();
    }

    public void checkUser(User user) throws InvalidLoginException, InvalidBirthdayException {

        if (user.getLogin().contains(" ")) {
            System.out.println("Wrong login");
            throw new InvalidLoginException("login contains spaces");
        }

        if (user.getName() == null || Objects.equals(user.getName(), " ") || Objects.equals(user.getName(), "")) {
            System.out.println("Setting login as name");
            user.setName(user.getLogin());
        }

        if (user.getBirthday().toInstant().isAfter(Instant.now())) {
            System.out.println("Wrong birthday date");
            throw new InvalidBirthdayException("Birthday in the future");
        }
    }

    public int generateId() {
        this.id++;
        return this.id;
    }

    public Integer getId() {
        return id;
    }
}
