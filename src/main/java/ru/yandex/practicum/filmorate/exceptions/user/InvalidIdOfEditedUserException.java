package ru.yandex.practicum.filmorate.exceptions.user;

import javax.validation.ValidationException;

public class InvalidIdOfEditedUserException extends ValidationException {
    public InvalidIdOfEditedUserException(String message) {
        super(message);
    }

    public InvalidIdOfEditedUserException() {
        super();
    }
}
