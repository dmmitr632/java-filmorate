package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    //добавление в друзья, удаление из друзей, вывод списка общих друзей.
    public User addToFriends(User user) {
        return user;
    }

    public User removeFromFriends(User user) {
        return user;
    }

    public List<User> viewMutualFriends(User user) {
        return Arrays.asList(user);
    }
}
