package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/user/add")
    public User adduser(@RequestBody User user) {
        log.info(user.toString());
        users.add(user);
        return user;
    }

    @PutMapping(value = "/user/edit")
    public User create(@RequestBody User user) {
        for (User userEdited: users) {
            if (userEdited.getId() == user.getId())
                users.remove(userEdited);

        }
        log.info(user.toString());
        users.add(user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> viewAllusers() {
        log.info(users.toString());
        return users;
    }

}
