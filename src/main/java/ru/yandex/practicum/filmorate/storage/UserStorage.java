package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserStorage {
    public User addUser(@Valid @RequestBody User user);

    public User editUser(@Valid @RequestBody User user);

    public List<User> viewAllUsers();

    public void validateUser(User user);

    public int generateId();
}
