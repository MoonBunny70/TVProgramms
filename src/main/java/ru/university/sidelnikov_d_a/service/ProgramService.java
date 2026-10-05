package ru.university.sidelnikov_d_a.service;

import ru.university.sidelnikov_d_a.exception.ChannelNotFoundException;
import ru.university.sidelnikov_d_a.exception.GenreNotFoundException;
import ru.university.sidelnikov_d_a.exception.ProgramNotFoundException;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;
import ru.university.sidelnikov_d_a.repository.ChannelRepository;
import ru.university.sidelnikov_d_a.repository.GenreRepository;
import ru.university.sidelnikov_d_a.repository.ProgramRepository;
import ru.university.sidelnikov_d_a.validator.ProgramValidator;

import java.util.List;

public class ProgramService {
    private ChannelRepository channelRepository;
    private GenreRepository genreRepository;
    private ProgramRepository programRepository;

    public ProgramService(ChannelRepository channelRepository, GenreRepository genreRepository, ProgramRepository programRepository) {
        this.channelRepository = channelRepository;
        this.genreRepository = genreRepository;
        this.programRepository = programRepository;
    }

    public List<Program> getAll() {
        return programRepository.getAll();
    }

    public Program getById(Long id) {
        Program program = programRepository.getById(id);

        if (program == null) {
            throw new ProgramNotFoundException(id);
        }

        return programRepository.getById(id);
    }

    public void add(Program program) {
        ProgramValidator.validate(program);

        validateChannelExists(program);
        validateGenreExists(program);

        programRepository.add(program);
    }

    public void update(Program program) {
        ProgramValidator.validate(program);

        if (programRepository.getById(program.getId()) == null) {
            throw new ProgramNotFoundException(program.getId());
        }

        validateChannelExists(program);
        validateGenreExists(program);

        programRepository.update(program);
    }

    public void delete(Long id) {
        if (programRepository.getById(id) == null) {
            throw new ProgramNotFoundException(id);
        }

        programRepository.delete(id);
    }

    private void validateChannelExists(Program program) {
        if (channelRepository.getById(program.getChannel().getId()) == null) {
            throw new ChannelNotFoundException(
                    program.getChannel().getId()
            );
        }
    }

    private void validateGenreExists(Program program) {
        if (genreRepository.getById(program.getGenre().getId()) == null) {
            throw new GenreNotFoundException(
                    program.getGenre().getId()
            );
        }
    }
}
