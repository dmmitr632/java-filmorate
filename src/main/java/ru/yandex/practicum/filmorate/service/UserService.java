package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final UserFriendStorage userFriendStorage;

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public UserFriendStorage getUserFriendStorage() {
        return userFriendStorage;
    }

    @Autowired
    public UserService(@Qualifier("userDb") UserStorage userStorage,
                       @Qualifier("friendsDb") UserFriendStorage userFriendStorage) {
        this.userStorage = userStorage;
        this.userFriendStorage = userFriendStorage;
    }

    public User getUserById(int userId) {
        return userStorage.getUserById(userId);
    }

    public User addUser(@Valid @RequestBody User user) {
        user =  userStorage.addUser(user);
        // System.out.println("User name " + user.getName());
        return user;
    }

    public User editUser(@Valid @RequestBody User user) {
        return userStorage.editUser(user);
    }

    public List<User> viewAllUsers() {
        return userStorage.viewAllUsers();
    }

    public void addToFriends(int user1Id, int user2Id) {
        userFriendStorage.addFriend(user1Id, user2Id);
    }

    public void removeFromFriends(int user1Id, int user2Id) {
        userFriendStorage.deleteFriend(user1Id, user2Id);
    }

    public Set<User> getCommonFriends(int user1Id, int user2Id) {
        return new HashSet<>(userFriendStorage.getCommonFriends(user1Id, user2Id));
    }

    public List<User> getAllFriendsForUser(int userId) {
        return new ArrayList<>(userFriendStorage.getUserFriends(userId));
    }
}

