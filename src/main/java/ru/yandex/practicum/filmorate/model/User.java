package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private int id;
    @NotBlank(message = "blank email")
    @NotEmpty(message = "empty email")
    @Email(message = "wrong email")
    private String email;
    @NotBlank
    @NotEmpty
    private String login;
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Set<Integer> usersIdsInFriends;
    private Set<Integer> likedFilmIds;
    //private

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
        usersIdsInFriends = new HashSet<>();
        likedFilmIds = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public Set<Integer> getUsersIdsInFriends() {
        return usersIdsInFriends;
    }

    public void addLikedFilmId(int filmId) {
        likedFilmIds.add(filmId);
    }

    public void removeLikedFilmId(int filmId) {
        likedFilmIds.remove(filmId);
    }

    public void addUsersInFriends(int friendId) {

        this.usersIdsInFriends.add(friendId);
    }

    public void removeUsersInFriends(int friendId) {
        this.usersIdsInFriends.remove(friendId);
    }
}
