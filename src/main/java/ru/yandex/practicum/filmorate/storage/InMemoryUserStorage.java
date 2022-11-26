package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int id;

    @Override
    @PostMapping
    public Optional<User> addUser(@Valid @RequestBody User user) {
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
        return Optional.of(user);
    }

    @Override
    @PutMapping
    public Optional<User> editUser(@Valid @RequestBody User user) {
        log.info("Trying to edit User with id {}, user.toString {} ", user.getId(), user);
        validateUser(user);
        for (User userEdited : users.values()) {
            if (userEdited.getId() == user.getId()) {
                users.replace(userEdited.getId(), user);
                log.info("Edited User with id {}, user.toString {} ", user.getId(), user);
                return Optional.of(user);
            }
        }
        throw new InvalidIdOfUserException();
    }

    @Override
    @GetMapping
    public List<User> viewAllUsers() {
        log.info("All users {} ", users);
        return new ArrayList<>(users.values());
    }

    @Override
    public Map<Integer, User> getUsers() {
        log.info("All users {} ", users);
        return users;
    }

    @Override
    public Optional<User> getUserById(int userId) {

        if (!users.containsKey(userId)) {
            throw new InvalidIdOfUserException();
        }

        log.info("User by id {}, {}", userId, this.users.get(userId));
        return Optional.of(this.users.get(userId));
    }

    @Override
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
