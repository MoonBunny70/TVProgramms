package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreRepositoryImpl implements GenreRepository{
    private final List<Genre> genres = new ArrayList<>();

    @Override
    public List<Genre> getAll() {
        return new ArrayList<>(genres);
    }

    @Override
    public Genre getById(Long id) {
        for (Genre genre : genres) {
            if (Objects.equals(genre.getId(), id)) {
                return genre;
            }
        }

        return null;
    }

    @Override
    public boolean add(Genre genre) {
        if (genre == null || genre.getId() == null) {
            return false;
        }

        if (getById(genre.getId()) != null) {
            return false;
        }

        genres.add(genre);
        return true;
    }

    @Override
    public boolean update(Genre genre) {
        Genre existingGenre = getById(genre.getId());

        if (existingGenre == null) {
            return false;
        }

        existingGenre.setName(genre.getName());

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Genre genre = getById(id);

        if (genre == null) {
            return false;
        }

        genres.remove(genre);

        return true;
    }
}
