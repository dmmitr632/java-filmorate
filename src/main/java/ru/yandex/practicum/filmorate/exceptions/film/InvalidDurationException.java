package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidDurationException extends ValidationException {
    public InvalidDurationException(String message) {
        super(message);
    }

    public InvalidDurationException() {
        super();
    }

    public InvalidDurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
