package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserStorage {
    User addUser(@Valid @RequestBody User user);

    User editUser(@Valid @RequestBody User user);

    List<User> viewAllUsers();

    void validateUser(User user);

    int generateId();
}
