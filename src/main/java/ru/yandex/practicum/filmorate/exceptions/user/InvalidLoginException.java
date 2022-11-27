package ru.yandex.practicum.filmorate.exceptions.user;

//import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.ValidationException;

public class InvalidLoginException extends ValidationException {
    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException() {
        super();
    }
}
