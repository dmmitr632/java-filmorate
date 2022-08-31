package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Duration;
import java.util.Date;

@Data
@Builder
public class Film {
    @NotNull
    private int id;
    @NotNull @NotBlank
    String name;
    @NotNull @NotBlank
    String description;
    @NotNull
    Date releaseDate;
    @NotNull @Positive
    Duration duration;
}
