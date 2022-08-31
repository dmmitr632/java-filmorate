package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class User {
    private int id;
    @NotNull @NotBlank @Email String email;
    @NotNull @NotBlank String login;
    String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birthday;
}
