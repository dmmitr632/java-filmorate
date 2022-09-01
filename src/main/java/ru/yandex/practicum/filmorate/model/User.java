package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class User {
    @Positive
    private int id;
    @NotNull @NotBlank @Email String email;
    @NotNull @NotBlank String login;
    String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }
}
