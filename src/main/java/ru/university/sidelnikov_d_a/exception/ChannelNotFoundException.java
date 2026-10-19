package ru.university.sidelnikov_d_a.exception;

public class ChannelNotFoundException extends RuntimeException {
    public ChannelNotFoundException(Long id) {
        super("Канал с id " + id + " не найден");
    }
}
