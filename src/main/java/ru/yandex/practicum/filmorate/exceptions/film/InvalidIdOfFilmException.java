package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidIdOfFilmException extends ValidationException {

    public InvalidIdOfFilmException() {
        super();
    }
    public InvalidIdOfFilmException(String message) {
        super(message);
    }

    public InvalidIdOfFilmException(String message, Throwable cause) {
        super(message, cause);
    }
}
