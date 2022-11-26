package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidDescriptionException extends ValidationException {
    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException() {
        super();
    }

    public InvalidDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
