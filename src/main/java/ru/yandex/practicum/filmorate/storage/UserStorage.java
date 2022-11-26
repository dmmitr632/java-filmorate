package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserStorage {
    Optional<User> addUser(@Valid @RequestBody User user);

    Optional<User> editUser(@Valid @RequestBody User user);

    List<User> viewAllUsers();

    Map<Integer, User> getUsers();

    Optional<User> getUserById(@Valid int userId);

    void validateUser(User user);

}
