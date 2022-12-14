package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Component
@Validated
@RestController
@RequestMapping("users")
public class UserController {
    protected final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public User editUser(@Valid @RequestBody User user) {
        return userService.editUser(user);
    }

    @GetMapping
    public List<User> viewAllUsers() {
        return userService.viewAllUsers();
    }

    @GetMapping("/{id}")
    public User viewUserByID(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void userAddsFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        userService.addToFriends(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{userId}")
    public void userRemovesFriend(@PathVariable Integer id, @PathVariable Integer userId) {
        userService.removeFromFriends(id, userId);
    }

    @GetMapping("{id}/friends")
    public List<User> viewAllFriendsOfUser(@PathVariable int id) {

        return userService.getAllFriendsForUser(id);
    }

    @GetMapping("{id}/friends/common/{otherId}")
    public Set<User> viewCommonFriendsOfUser(@PathVariable int id, @PathVariable int otherId) {
        return userService.getCommonFriends(id, otherId);
    }
}
