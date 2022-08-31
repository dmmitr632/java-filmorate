package ru.yandex.practicum.filmorate.exceptions.film;

public class InvalidIdOfEditedFilmException extends Throwable {
    public InvalidIdOfEditedFilmException(String message) {
        super(message);
    }

    public InvalidIdOfEditedFilmException() {
        super();
    }
}
