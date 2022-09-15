package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    //добавление в друзья, удаление из друзей, вывод списка общих друзей.
    public void addToFriends(User user1, User user2) {
        user1.addUsersInFriends(user2.getId());
        user2.addUsersInFriends(user1.getId());
    }

    public void removeFromFriends(User user1, User user2) {
        user1.removeUsersInFriends(user2.getId());
        user2.removeUsersInFriends(user1.getId());
    }

    public Set<Integer> viewCommonFriends(User user1, User user2) {

        Set<Integer> commonFriendsIds = new HashSet<>();
        for (Integer friendId : user1.getUsersIdsInFriends()) {
            if (user2.getUsersIdsInFriends().contains(friendId)) {
                commonFriendsIds.add(friendId);
            }
        }
        return commonFriendsIds;
    }
}
