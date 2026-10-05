package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramRepositoryImpl implements ProgramRepository {
    private List<Program> programs = new ArrayList<>();

    @Override
    public List<Program> getAll() {
        return programs;
    }

    @Override
    public Program getById(Long id) {
        for (Program program : programs) {
            if (program.getId().equals(id)) {
                return program;
            }
        }

        return null;
    }

    @Override
    public boolean add(Program program) {
        programs.add(program);
        return true;
    }

    @Override
    public boolean update(Program program) {
        Program existingProgram = getById(program.getId());

        if (existingProgram != null) {
            existingProgram.setName(program.getName());
            existingProgram.setChannel(program.getChannel());
            existingProgram.setGenre(program.getGenre());
            existingProgram.setStartTime(program.getStartTime());
            existingProgram.setDayOfWeek(program.getDayOfWeek());

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        Program program = getById(id);

        if (program != null) {
            programs.remove(program);
            return true;
        }

        return false;
    }
}
