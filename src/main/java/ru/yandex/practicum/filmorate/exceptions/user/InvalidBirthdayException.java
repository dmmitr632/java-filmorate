package ru.yandex.practicum.filmorate.exceptions.user;

//import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import javax.validation.ValidationException;

public class InvalidBirthdayException extends ValidationException {
    public InvalidBirthdayException(String message) {
        super(message);
    }

    public InvalidBirthdayException() {
        super();
    }
}
