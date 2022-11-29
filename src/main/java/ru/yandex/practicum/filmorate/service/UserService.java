package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserFriendStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
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
        return userStorage.addUser(user);
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

    public Set<User> getAllFriendsForUser(int userId) {
        return new HashSet<>(userFriendStorage.getUserFriends(userId));
    }
}

//    public void addToFriends(int user1Id, int user2Id) {
//        //
//        //        userStorage.getUserById(user1Id).addUsersInFriends(user2Id);
//        //
//        //        userStorage.getUserById(user2Id).addUsersInFriends(user1Id);
//    }
//
//    public void removeFromFriends(int user1Id, int user2Id) {
//
//        //        if ((!userStorage.getUserById(user1Id).getUsersIdsInFriends().contains(user2Id)) ||
//        //                (!userStorage.getUserById(user2Id).getUsersIdsInFriends().contains(user1Id))) {
//        //            throw new InvalidIdOfUserException();
//        //        }
//        //
//        //        userStorage.getUserById(user1Id).removeUsersInFriends(user2Id);
//        //        userStorage.getUserById(user2Id).removeUsersInFriends(user1Id);
//    }
//
//    public Set<User> getCommonFriends(int user1Id, int user2Id) {
//
//        //        Set<User> commonFriends = new HashSet<>();
//        //        for (Integer friendId : userStorage.getUserById(user1Id).getUsersIdsInFriends()) {
//        //            if (userStorage.getUserById(user2Id).getUsersIdsInFriends().contains(friendId)) {
//        //                commonFriends.add(userStorage.getUserById(friendId));
//        //            }
//        //        }
//        //        return commonFriends;
//        return null;
//    }
//
//    public Set<User> getAllFriendsForUser(int userId) {
//        //
//        //        Set<User> friendsOfUser = new HashSet<>();
//        //        for (Integer friendId : userStorage.getUserById(userId).getUsersIdsInFriends()) {
//        //
//        //            friendsOfUser.add(userStorage.getUserById(friendId));
//        //        }
//
//        //        return friendsOfUser;
//        return null;
//    }