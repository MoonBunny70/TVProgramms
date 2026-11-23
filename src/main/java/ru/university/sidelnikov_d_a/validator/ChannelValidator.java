package ru.university.sidelnikov_d_a.validator;

import ru.university.sidelnikov_d_a.model.Channel;

public class ChannelValidator {
    public static void validate(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException(
                    "Канал не может быть null"
            );
        }

        if (channel.getName() == null || channel.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Название канала не может быть пустым"
            );
        }
    }
}
