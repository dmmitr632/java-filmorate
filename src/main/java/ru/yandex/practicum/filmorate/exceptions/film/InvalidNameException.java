package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidNameException extends ValidationException {
    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException() {
        super();
    }

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
