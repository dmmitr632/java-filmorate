package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserFriendStorage {
    List<User> getUserSubscriptions(int id);

    List<User> getUserFriends(int id);

    List<User> getCommonSubscriptions(int id, int friendId);

    void addSubscription(int userId, int friendId);

    void deleteSubscription(int id, int friendId);
}
