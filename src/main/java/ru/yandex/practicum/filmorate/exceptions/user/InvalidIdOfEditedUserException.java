package ru.yandex.practicum.filmorate.exceptions.user;

public class InvalidIdOfEditedUserException extends Throwable {
    public InvalidIdOfEditedUserException(String message) {
        super(message);
    }

    public InvalidIdOfEditedUserException() {
        super();
    }
}
