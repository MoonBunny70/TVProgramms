package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryImpl implements GenreRepository{
    private List<Genre> genres = new ArrayList<>();

    @Override
    public List<Genre> getAll() {
        return genres;
    }

    @Override
    public Genre getById(Long id) {
        for (Genre genre : genres) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }

        return null;
    }

    @Override
    public boolean add(Genre genre) {
        genres.add(genre);
        return true;
    }

    @Override
    public boolean update(Genre genre) {
        Genre existingGenre = getById(genre.getId());

        if (existingGenre != null) {
            existingGenre.setName(genre.getName());
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        Genre genre = getById(id);

        if (genre != null) {
            genres.remove(genre);
            return true;
        }

        return false;
    }
}
