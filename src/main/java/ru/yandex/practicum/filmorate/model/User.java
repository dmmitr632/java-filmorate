package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;


@Data
@Builder
public class User {
    @NonNull
    int id;
    String email;
    @NonNull
    String name;
    @NonNull
    Date birthday;
}
