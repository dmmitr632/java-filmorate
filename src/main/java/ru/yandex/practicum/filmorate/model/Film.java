package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Builder
public class Film {
    private int id;
    @NotNull @NotBlank String name;
    @NotNull @NotBlank String description;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date releaseDate;
    @NotNull int duration;
}
