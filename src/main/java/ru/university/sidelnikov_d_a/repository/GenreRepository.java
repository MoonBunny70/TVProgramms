package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getAll();

    Genre getById(Long id);

    boolean add(Genre genre);

    boolean update(Genre genre);

    boolean delete(Long id);
}
