package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.controller.UserController;

@SpringBootApplication
public class FilmorateApplication {
    public static void main(String[] args) {
        //SpringApplication.run(FilmorateApplication.class, args);
        ApplicationContext context = SpringApplication.run(FilmorateApplication.class, args);
        FilmController filmController = context.getBean(FilmController.class);
        UserController userController = context.getBean(UserController.class);
    }
}
