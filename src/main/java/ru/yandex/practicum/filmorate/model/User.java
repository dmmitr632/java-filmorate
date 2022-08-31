package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@Builder
public class User {
    @NotNull
    int id;
    @NotNull @NotBlank @Email
    String email;
    @NotNull @NotBlank
    String login;
    String name;
    @NotNull
    Date birthday;
}
