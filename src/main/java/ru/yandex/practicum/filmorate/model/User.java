package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public Set<Integer> getUsersIdsInFriends() {
        return usersIdsInFriends;
    }

    //    public Set<User> getUsersInFriends() {
    //        Set<User>
    //    }

    //    public Set<Integer> getLikedFilmIds() {
    //        return likedFilmIds;
    //    }

    public void addLikedFilmId(int filmId) {
        likedFilmIds.add(filmId);
    }

    public void removeLikedFilmId(int filmId) {
        likedFilmIds.remove(filmId);
    }

    public void addUsersInFriends(int userIdsWhoLikedFilm) {
        this.usersIdsInFriends.add(userIdsWhoLikedFilm);
    }

    public void removeUsersInFriends(int userIdsWhoLikedFilm) {
        this.usersIdsInFriends.remove(userIdsWhoLikedFilm);
    }
}
