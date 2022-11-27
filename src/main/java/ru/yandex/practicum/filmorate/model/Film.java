package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.HashSet;

@Data
@Builder
public class Film {
    private Integer id;
    private Mpa mpa;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String description;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate releaseDate;
    @NotNull
    @PositiveOrZero
    private Integer duration;
    @PositiveOrZero
    private Integer rate;
    private HashSet<Genre> genres;
    // private Set<Integer> userIdsWhoLikedFilm;

    public int getMpaId() {
        return mpa.getId();
    }
}
