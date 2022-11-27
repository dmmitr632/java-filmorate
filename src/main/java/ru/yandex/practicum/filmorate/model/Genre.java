package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {
    //    Comedy,
    //    Drama,
    //    Cartoon,
    //    Thriller,
    //    Documentary,
    //    Action
    private Integer id;
    private String name;
}
