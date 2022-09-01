package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidIdOfEditedFilmException extends ValidationException {
    public InvalidIdOfEditedFilmException(String message) {
        super(message);
    }

    public InvalidIdOfEditedFilmException() {
        super();
    }
}
