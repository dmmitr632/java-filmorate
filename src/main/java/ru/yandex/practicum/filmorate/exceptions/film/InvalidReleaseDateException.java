package ru.yandex.practicum.filmorate.exceptions.film;

public class InvalidReleaseDateException extends Throwable {
    public InvalidReleaseDateException(String message) {
        super(message);
    }

    public InvalidReleaseDateException() {
        super();
    }
}
