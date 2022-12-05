package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mpa {
    //G — у фильма нет возрастных ограничений,
    //PG — детям рекомендуется смотреть фильм с родителями,
    //PG-13 — детям до 13 лет просмотр не желателен,
    //R — лицам до 17 лет просматривать фильм можно только в присутствии взрослого,
    //NC-17 — лицам до 18 лет просмотр запрещён.
    //    G,
    //    PG,
    //    PG_13,
    //    R,
    //    NC_17
    private Integer id;
    private String name;
}
