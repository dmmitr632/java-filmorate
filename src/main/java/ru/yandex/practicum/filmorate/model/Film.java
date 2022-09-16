package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    private int id;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String description;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @NotNull
    @PositiveOrZero
    private int duration;
    private Set<Integer> userIdsWhoLikedFilm;

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        userIdsWhoLikedFilm = new HashSet<>();
    }

    public Set<Integer> getUsersWhoLikedFilm() {
        return userIdsWhoLikedFilm;
    }

    public void addUsersWhoLikedFilm(int userIdsWhoLikedFilm) {
        this.userIdsWhoLikedFilm.add(userIdsWhoLikedFilm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)) {
            return false;
        }

        Film film = (Film) o;

        return getId() == film.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public void removeUsersWhoLikedFilm(int userIdsWhoLikedFilm) {
        this.userIdsWhoLikedFilm.remove(userIdsWhoLikedFilm);
    }
}
