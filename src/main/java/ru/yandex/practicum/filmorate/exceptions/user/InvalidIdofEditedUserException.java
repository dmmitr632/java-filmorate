package ru.yandex.practicum.filmorate.exceptions.user;

public class InvalidIdofEditedUserException extends Throwable {
    public InvalidIdofEditedUserException(String message) {
        super(message);
    }

    public InvalidIdofEditedUserException() {
        super();
    }
}
