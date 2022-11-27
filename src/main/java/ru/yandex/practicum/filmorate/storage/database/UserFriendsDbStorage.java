package ru.yandex.practicum.filmorate.storage.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;

import java.util.List;

@Component
@Qualifier("friendsDb")
public class UserFriendsDbStorage implements UserFriendStorage {
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
