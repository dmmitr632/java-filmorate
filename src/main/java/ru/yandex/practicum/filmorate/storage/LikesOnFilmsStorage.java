package ru.yandex.practicum.filmorate.storage;

public interface LikesOnFilmsStorage {
    void addLike(int filmId, int userId);

    void deleteLike(int filmId, int userId);
}
