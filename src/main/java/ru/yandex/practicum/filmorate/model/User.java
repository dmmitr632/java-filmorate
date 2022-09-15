package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
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
    @PositiveOrZero
    private Set<User> usersInFriends;

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }
}
