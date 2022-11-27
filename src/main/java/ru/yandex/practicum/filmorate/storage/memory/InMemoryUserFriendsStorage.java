package ru.yandex.practicum.filmorate.storage.memory;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;

import java.util.List;

public class InMemoryUserFriendsStorage implements UserFriendStorage {
    //TODO: заполнить
    @Override
    public List<User> getUserSubscriptions(int id) {
        return null;
    }

    @Override
    public List<User> getUserFriends(int id) {
        return null;
    }

    @Override
    public List<User> getCommonSubscriptions(int id, int friendId) {
        return null;
    }

    @Override
    public void addSubscription(int userId, int friendId) {

    }

    @Override
    public void deleteSubscription(int id, int friendId) {

    }
}
