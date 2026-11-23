package ru.university.sidelnikov_d_a.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(Long id) {
        super("Программа с id " + id + " не найден");
    }
}
