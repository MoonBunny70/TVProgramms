package ru.university.sidelnikov_d_a.repository.ArrayList;

import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;
import ru.university.sidelnikov_d_a.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramRepositoryImpl {
    private final List<Program> programs = new ArrayList<>();

    public List<Program> getAll() {
        return new ArrayList<>(programs);
    }

    public Program getById(Long id) {
        for (Program program : programs) {
            if (Objects.equals(program.getId(), id)) {
                return program;
            }
        }

        return null;
    }

    public List<Program> getByChannel(Channel channel) {
        List<Program> result = new ArrayList<>();

        for (Program program : programs) {
            if (Objects.equals(program.getChannel(), channel)) {
                result.add(program);
            }
        }

        return result;
    }

    public List<Program> getByGenre(Genre genre) {
        List<Program> result = new ArrayList<>();

        for (Program program : programs) {
            if (Objects.equals(program.getGenre(), genre)) {
                result.add(program);
            }
        }

        return result;
    }

    public boolean add(Program program) {
        if (program == null || program.getId() == null) {
            return false;
        }

        // Дублирование ID
        if (getById(program.getId()) != null) {
            return false;
        }

        programs.add(program);
        return true;
    }

    public boolean update(Program program) {
        if (program == null || program.getId() == null) {
            return false;
        }

        Program existingProgram = getById(program.getId());

        if (existingProgram == null) {
            return false;
        }

        existingProgram.setName(program.getName());
        existingProgram.setChannel(program.getChannel());
        existingProgram.setGenre(program.getGenre());
        existingProgram.setStartTime(program.getStartTime());
        existingProgram.setDayOfWeek(program.getDayOfWeek());

        return true;
    }

    public boolean delete(Long id) {
        Program program = getById(id);

        if (program == null) {
            return false;
        }

        programs.remove(program);
        return true;
    }
}
