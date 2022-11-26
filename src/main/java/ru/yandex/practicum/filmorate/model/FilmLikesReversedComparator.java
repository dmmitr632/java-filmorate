package ru.yandex.practicum.filmorate.model;

import java.util.Comparator;

public class FilmLikesReversedComparator implements Comparator<Film> {
    @Override
    public int compare(Film film1, Film film2) {

        //        if (film1.getUsersWhoLikedFilm().size() > film2.getUsersWhoLikedFilm().size()) {
        //            return -1;
        //        } else if (film1.getUsersWhoLikedFilm().size() < film2.getUsersWhoLikedFilm().size()) {
        //            return 1;
        //        } else {
        //            return 0;
        //        }
        return (0);
    }
}