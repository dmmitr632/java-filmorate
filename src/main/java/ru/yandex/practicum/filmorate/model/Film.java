package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.Date;

@Data
@Builder
public class Film {
    @NonNull
    private int id;
    @NonNull
    String name;
    String description;
    Date releaseDate;
    Duration duration;
}
