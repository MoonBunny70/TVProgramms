package ru.university.sidelnikov_d_a.validator;

import ru.university.sidelnikov_d_a.model.Program;

public class ProgramValidator {
    public static void validate(Program program) {
        if (program == null) {
            throw new IllegalArgumentException(
                    "Программа не может быть null"
            );
        }

        if (program.getName() == null || program.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Название программы не может быть пустым"
            );
        }

        if (program.getDayOfWeek() == null) {
            throw new IllegalArgumentException(
                    "День недели не может быть null"
            );
        }

        if (program.getStartTime() == null) {
            throw new IllegalArgumentException(
                    "Время начала не может быть null"
            );
        }

        ChannelValidator.validate(program.getChannel());
        GenreValidator.validate(program.getGenre());
    }
}
