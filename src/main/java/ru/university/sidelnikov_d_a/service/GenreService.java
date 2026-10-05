package ru.university.sidelnikov_d_a.service;

import ru.university.sidelnikov_d_a.exception.GenreNotFoundException;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.repository.GenreRepository;
import ru.university.sidelnikov_d_a.validator.GenreValidator;

import java.util.List;

public class GenreService {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    public Genre getById(Long id) {
        Genre genre = genreRepository.getById(id);

        if (genre == null) {
            throw new GenreNotFoundException(id);
        }

        return genre;
    }

    public void add(Genre genre) {
        GenreValidator.validate(genre);

        genreRepository.add(genre);
    }

    public void update(Genre genre) {
        GenreValidator.validate(genre);

        if (genreRepository.getById(genre.getId()) == null) {
            throw new GenreNotFoundException(genre.getId());
        }

        genreRepository.update(genre);
    }

    public void delete(Long id) {
        if (genreRepository.getById(id) == null) {
            throw new GenreNotFoundException(id);
        }

        genreRepository.delete(id);
    }
}
