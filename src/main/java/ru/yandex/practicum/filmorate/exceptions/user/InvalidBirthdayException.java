package ru.yandex.practicum.filmorate.exceptions.user;

public class InvalidBirthdayException extends Throwable {
    public InvalidBirthdayException(String message) {
        super(message);
    }

    public InvalidBirthdayException() {
        super();
    }
}
