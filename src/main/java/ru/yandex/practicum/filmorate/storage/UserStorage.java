package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface UserStorage {
    User addUser(@Valid @RequestBody User user);

    User editUser(@Valid @RequestBody User user);

    List<User> viewAllUsers();
    Map<Integer, User> getUsers();

    User getUser(@Valid @RequestBody User user);

    User getUserById(@Valid int userId);

    void validateUser(User user);

    int generateId();
}
