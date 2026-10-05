package ru.university.sidelnikov_d_a.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramRepositoryImplTest {
    @Test
    @DisplayName("Добавление программы")
    void addProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Channel channel = new Channel("СТС");
        Genre genre = new Genre("Комедия");
        Program program = new Program("Первая", channel, genre, null, null);

        boolean result = programRepository.add(program);

        assertTrue(result);

        assertEquals(1, programRepository.getAll().size());
        assertEquals("Первая", programRepository.getById(program.getId()).getName());
        assertEquals("СТС", programRepository.getById(program.getId()).getChannel().getName());
        assertEquals("Комедия", programRepository.getById(program.getId()).getGenre().getName());
        assertNull(programRepository.getById(program.getId()).getStartTime());
        assertNull(programRepository.getById(program.getId()).getDayOfWeek());
    }

    @Test
    @DisplayName("Существующая программа находится")
    void getByIdProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Channel channel = new Channel("СТС");
        Genre genre = new Genre("Комедия");
        Program program = new Program("Первая", channel, genre, null, null);

        programRepository.add(program);

        Program result = programRepository.getById(program.getId());

        assertNotNull(result);
        assertEquals("Первая", programRepository.getById(program.getId()).getName());
        assertEquals("СТС", programRepository.getById(program.getId()).getChannel().getName());
        assertEquals("Комедия", programRepository.getById(program.getId()).getGenre().getName());
        assertNull(programRepository.getById(program.getId()).getStartTime());
        assertNull(programRepository.getById(program.getId()).getDayOfWeek());
    }

    @Test
    @DisplayName("Несуществующая программа не находится")
    void getByIdNullProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Program result = programRepository.getById(999L);

        assertNull(result);
    }

    @Test
    @DisplayName("Изменение программы")
    void updateProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Channel channel = new Channel("СТС");
        Genre genre = new Genre("Комедия");
        Program program = new Program("Первая", channel, genre, null, null);

        programRepository.add(program);

        Program updatedProgram = new Program(program.getId(),"ТНТ HD", channel, genre, null, null);

        boolean result = programRepository.update(updatedProgram);

        assertTrue(result);
        assertEquals("ТНТ HD", programRepository.getById(program.getId()).getName());
        assertEquals("СТС", programRepository.getById(program.getId()).getChannel().getName());
        assertEquals("Комедия", programRepository.getById(program.getId()).getGenre().getName());
        assertNull(programRepository.getById(program.getId()).getStartTime());
        assertNull(programRepository.getById(program.getId()).getDayOfWeek());
    }

    @Test
    @DisplayName("Изменение несуществующей программы")
    void updateNotFoundProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Channel channel = new Channel("СТС");
        Genre genre = new Genre("Комедия");
        Program program = new Program(999L,"Несуществующая программа", channel, genre, null, null);

        boolean result = programRepository.update(program);

        assertFalse(result);
    }

    @Test
    @DisplayName("Удаление программы")
    void deleteProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        Channel channel = new Channel("СТС");
        Genre genre = new Genre("Комедия");
        Program program = new Program("Первая", channel, genre, null, null);

        programRepository.add(program);

        boolean result = programRepository.delete(program.getId());

        assertTrue(result);
        assertNull(programRepository.getById(program.getId()));
    }

    @Test
    @DisplayName("Удаление несуществующей программы")
    void deleteNotFoundProgram() {
        ProgramRepository programRepository = new ProgramRepositoryImpl();

        boolean result = programRepository.delete(999L);

        assertFalse(result);
    }
}
