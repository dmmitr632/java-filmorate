package ru.yandex.practicum.filmorate.controller;
//package ru.yandex.practicum.filmorate.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import ru.yandex.practicum.filmorate.exceptions.film.InvalidIdOfFilmException;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidBirthdayException;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidIdOfUserException;
//import ru.yandex.practicum.filmorate.exceptions.user.InvalidLoginException;
//import ru.yandex.practicum.filmorate.model.ErrorResponse;
//
//@RestControllerAdvice
//public class ErrorHandler {
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
//    public ErrorResponse handleInvalidBirthdayException(final InvalidBirthdayException e) {
//        return new ErrorResponse("My ErrorHandler " + e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
//    public ErrorResponse handleInvalidLoginException(final InvalidLoginException e) {
//        return new ErrorResponse("My ErrorHandler " + e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND) //404
//    public ErrorResponse handleUserNotFoundException(final InvalidIdOfUserException e) {
//        return new ErrorResponse(e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND) //404
//    public ErrorResponse handleFilmNotFoundException(final InvalidIdOfFilmException e) {
//        return new ErrorResponse(e.getMessage());
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
//    public ErrorResponse handleThrowable(final Throwable e) {
//        return new ErrorResponse("My ErrorHandler " + "Произошла непредвиденная ошибка.");
//    }
//}


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import ru.yandex.practicum.filmorate.model.ErrorResponse;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorResponse handleValidationException(final ValidationException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    public ErrorResponse handleNotFoundException(final ChangeSetPersister.NotFoundException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ErrorResponse handleInternalServerErrorException(final HttpServerErrorException.InternalServerError e) {
        return new ErrorResponse("error", e.getMessage());
    }

}
