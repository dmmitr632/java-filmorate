package ru.yandex.practicum.filmorate.exceptions.film;

public class InvalidNameException extends Throwable {

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException() {
        super();
    }

}
