package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    //добавление в друзья, удаление из друзей, вывод списка общих друзей.
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addToFriends(int user1Id, int user2Id) {
        System.out.println("addToFriends(UserService) ");
        if ((!userStorage.getUsers().containsKey(user1Id)) || (!userStorage.getUsers().containsKey(user2Id))) {
            throw new InvalidIdOfUserException();
        }
        userStorage.getUserById(user1Id).addUsersInFriends(user2Id);
        System.out.println("Friends " + userStorage.getUserById(user1Id).getUsersIdsInFriends());
        userStorage.getUserById(user2Id).addUsersInFriends(user1Id);
        System.out.println("Friends " + userStorage.getUserById(user2Id).getUsersIdsInFriends());
    }

    public void removeFromFriends(int user1Id, int user2Id) {

        if ((!userStorage.getUsers().containsKey(user1Id)) || (!userStorage.getUsers().containsKey(user2Id))) {
            throw new InvalidIdOfUserException();
        }
        if ((!userStorage.getUserById(user1Id).getUsersIdsInFriends().contains(user2Id)) ||
                (!userStorage.getUserById(user2Id).getUsersIdsInFriends().contains(user1Id))) {
            throw new InvalidIdOfUserException();
        }
        System.out.println("UserService removeFromFriends");

        userStorage.getUserById(user1Id).removeUsersInFriends(user2Id);
        userStorage.getUserById(user2Id).removeUsersInFriends(user1Id);
    }

    public Set<User> getCommonFriends(int user1Id, int user2Id) {

        Set<User> commonFriends = new HashSet<>();
        for (Integer friendId : userStorage.getUserById(user1Id).getUsersIdsInFriends()) {
            if (userStorage.getUserById(user2Id).getUsersIdsInFriends().contains(friendId)) {
                commonFriends.add(userStorage.getUserById(friendId));
            }
        }
        return commonFriends;
    }

    public Set<User> getAllFriendsForUser(int userId) {

        Set<User> friendsOfUser = new HashSet<>();
        for (Integer friendId : userStorage.getUserById(userId).getUsersIdsInFriends()) {

            friendsOfUser.add(userStorage.getUserById(friendId));
        }
        System.out.println("UserService getAllFriendsForUser");
        return friendsOfUser;
    }
}
