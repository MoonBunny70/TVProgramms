package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getAll();

    Genre getById(Long id);

    Genre add(Genre genre);

    Genre update(Genre genre);

    Genre delete(Long id);
}
