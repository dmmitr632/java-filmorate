package ru.yandex.practicum.filmorate.exceptions.film;

public class InvalidDescriptionException extends Throwable {

    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException() {
        super();
    }

}
