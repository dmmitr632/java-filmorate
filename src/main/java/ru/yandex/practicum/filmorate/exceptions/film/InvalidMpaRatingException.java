package ru.yandex.practicum.filmorate.exceptions.film;

import javax.validation.ValidationException;

public class InvalidMpaRatingException extends ValidationException {
    public InvalidMpaRatingException(String message) {
        super(message);
    }

    public InvalidMpaRatingException() {
        super();
    }

    public InvalidMpaRatingException(String message, Throwable cause) {
        super(message, cause);
    }
}