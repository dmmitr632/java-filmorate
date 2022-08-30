package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    @PostMapping("/user/add")
    public User adduser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @PutMapping(value = "/user/edit")
    public User create(@RequestBody User user) {
        for (User userEdited: users) {
            if (userEdited.getId() == user.getId())
                users.remove(userEdited);

        }
        users.add(user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> viewAllusers() {
        return users;
    }

}
