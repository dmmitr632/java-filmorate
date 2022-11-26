package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidReleaseDateException extends ValidationException {
    public InvalidReleaseDateException(String message) {
        super(message);
    }

    public InvalidReleaseDateException() {
        super();
    }

    public InvalidReleaseDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
