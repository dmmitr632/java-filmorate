package ru.yandex.practicum.filmorate.exceptions.user;

import javax.validation.ValidationException;

public class InvalidIdOfUserException extends ValidationException {
    public InvalidIdOfUserException(String message) {
        super(message);
    }

    public InvalidIdOfUserException() {
        super();
    }
}
