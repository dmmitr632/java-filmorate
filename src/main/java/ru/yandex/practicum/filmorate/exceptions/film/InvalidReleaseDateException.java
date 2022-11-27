package ru.yandex.practicum.filmorate.exceptions.film;

//import ru.yandex.practicum.filmorate.exceptions.ValidationException;

//import javax.validation.ValidationException;

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
