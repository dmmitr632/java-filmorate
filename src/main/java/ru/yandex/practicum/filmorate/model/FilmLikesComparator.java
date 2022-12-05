package ru.yandex.practicum.filmorate.model;

import java.util.Comparator;

public class FilmLikesComparator implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {

        if (film1.getRate() > film2.getRate()) {
            return 1;
        } else if (film1.getRate() < film2.getRate()) {
            return -1;
        } else {
            return 0;
        }
    }
}