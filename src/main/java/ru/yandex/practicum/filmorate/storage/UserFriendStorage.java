package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserFriendStorage {

    List<User> getUserFriends(int id);

    List<User> getCommonFriends(int id, int friendId);

    void addFriend(int userId, int friendId);

    void deleteFriend(int id, int friendId);
}
