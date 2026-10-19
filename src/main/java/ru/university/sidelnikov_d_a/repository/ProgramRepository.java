package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;

import java.util.List;

public interface ProgramRepository {
    List<Program> getAll();

    Program getById(Long id);

    List<Program> getByChannel(Channel channel);

    List<Program> getByGenre(Genre genre);

    boolean add(Program program);

    boolean update(Program program);

    boolean delete(Long id);
}
