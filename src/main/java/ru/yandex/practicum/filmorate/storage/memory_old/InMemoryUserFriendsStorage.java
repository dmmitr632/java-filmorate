package ru.yandex.practicum.filmorate.storage.memory_old;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;

import java.util.List;

public class InMemoryUserFriendsStorage implements UserFriendStorage {
    //TODO: заполнить


    @Override
    public List<User> getUserFriends(int id) {
        return null;
    }

    @Override
    public List<User> getCommonFriends(int id, int friendId) {
        return null;
    }

    @Override
    public void addFriend(int userId, int friendId) {

    }

    @Override
    public void deleteFriend(int id, int friendId) {

    }

}
