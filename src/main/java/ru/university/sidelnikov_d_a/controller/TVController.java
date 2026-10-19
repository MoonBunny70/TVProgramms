package ru.university.sidelnikov_d_a.controller;

import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;
import ru.university.sidelnikov_d_a.service.ChannelService;
import ru.university.sidelnikov_d_a.service.GenreService;
import ru.university.sidelnikov_d_a.service.ProgramService;

import java.util.List;

public class TVController {
    private final ChannelService channelService;
    private final GenreService genreService;
    private final ProgramService programService;

    public TVController(
            ChannelService channelService,
            GenreService genreService,
            ProgramService programService
    ) {
        this.channelService = channelService;
        this.genreService = genreService;
        this.programService = programService;
    }

    // Программы
    public List<Program> getAllPrograms() {
        return programService.getAll();
    }

    public Program getProgramById(Long id) {
        return programService.getById(id);
    }

    public boolean addProgram(Program program) {
        return programService.add(program);
    }

    public boolean updateProgram(Program program) {
        return programService.update(program);
    }

    public boolean deleteProgram(Long id) {
        return programService.delete(id);
    }

    public int getProgramCount() {
        return programService.getAll().size();
    }

    // Каналы
    public List<Channel> getAllChannels() {
        return channelService.getAll();
    }

    public Channel getChannelById(Long id) {
        return channelService.getById(id);
    }

    public boolean addChannel(Channel channel) {
        return channelService.add(channel);
    }

    public boolean updateChannel(Channel channel) {
        return channelService.update(channel);
    }

    public boolean deleteChannel(Long id) {
        return channelService.delete(id);
    }

    public int getChannelCount() {
        return channelService.getAll().size();
    }

    // Жанры
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    public Genre getGenreById(Long id) {
        return genreService.getById(id);
    }

    public boolean addGenre(Genre genre) {
        return genreService.add(genre);
    }

    public boolean updateGenre(Genre genre) {
        return genreService.update(genre);
    }

    public boolean deleteGenre(Long id) {
        return genreService.delete(id);
    }

    public int getGenreCount() {
        return genreService.getAll().size();
    }
}
