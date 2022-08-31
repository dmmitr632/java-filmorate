package ru.yandex.practicum.filmorate.exceptions.user;

public class InvalidLoginException extends Throwable {
    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException() {
        super();
    }
}
