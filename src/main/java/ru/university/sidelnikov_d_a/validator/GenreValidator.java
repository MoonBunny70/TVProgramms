package ru.university.sidelnikov_d_a.validator;

import ru.university.sidelnikov_d_a.model.Genre;

public class GenreValidator {
    public static void validate(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException(
                    "Жанр не может быть null"
            );
        }

        if (genre.getName() == null || genre.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Название жанра не может быть пустым"
            );
        }
    }
}
