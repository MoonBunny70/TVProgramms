package ru.university.sidelnikov_d_a.service;

import ru.university.sidelnikov_d_a.exception.GenreNotFoundException;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.repository.GenreRepository;
import ru.university.sidelnikov_d_a.repository.ProgramRepository;
import ru.university.sidelnikov_d_a.validator.GenreValidator;

import java.util.List;

public class GenreService {
    private final GenreRepository genreRepository;
    private final ProgramRepository programRepository;

    public GenreService(GenreRepository genreRepository, ProgramRepository programRepository) {
        this.genreRepository = genreRepository;
        this.programRepository = programRepository;
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

    public boolean add(Genre genre) {
        GenreValidator.validate(genre);

        return genreRepository.add(genre);
    }

    public boolean update(Genre genre) {
        GenreValidator.validate(genre);

        if (genreRepository.getById(genre.getId()) == null) {
            throw new GenreNotFoundException(genre.getId());
        }

        return genreRepository.update(genre);
    }

    public boolean delete(Long id) {
        Genre genre = getById(id);

        if (genreRepository.getById(id) == null) {
            throw new GenreNotFoundException(id);
        }

        if (!programRepository.getByGenre(genre).isEmpty()) {
            throw new IllegalStateException(
                    "Нельзя удалить жанр, пока он используется в других программах"
            );
        }

        return genreRepository.delete(id);
    }
}
