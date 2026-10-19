package ru.university.sidelnikov_d_a.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Жанр с id " + id + " не найден");
    }
}
