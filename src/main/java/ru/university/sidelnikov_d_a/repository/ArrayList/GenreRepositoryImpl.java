package ru.university.sidelnikov_d_a.repository.ArrayList;

import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreRepositoryImpl {
    private final List<Genre> genres = new ArrayList<>();

    public List<Genre> getAll() {
        return new ArrayList<>(genres);
    }

    public Genre getById(Long id) {
        for (Genre genre : genres) {
            if (Objects.equals(genre.getId(), id)) {
                return genre;
            }
        }

        return null;
    }

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

    public boolean update(Genre genre) {
        Genre existingGenre = getById(genre.getId());

        if (existingGenre == null) {
            return false;
        }

        existingGenre.setName(genre.getName());

        return true;
    }

    public boolean delete(Long id) {
        Genre genre = getById(id);

        if (genre == null) {
            return false;
        }

        genres.remove(genre);

        return true;
    }
}
