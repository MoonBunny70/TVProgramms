package ru.university.sidelnikov_d_a.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.university.sidelnikov_d_a.model.Channel;

import static org.junit.jupiter.api.Assertions.*;

public class ChannelRepositoryImplTest {

    @Test
    @DisplayName("Добавление канала")
    void addChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel channel = new Channel("СТС");

        boolean result = repository.add(channel);

        assertTrue(result);
        assertEquals(1, repository.getAll().size());
        assertEquals("СТС", repository.getAll().get(0).getName());
    }

    @Test
    @DisplayName("Существующий канал находится")
    void getByIdChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel channel = new Channel("ТНТ");
        repository.add(channel);

        Channel result = repository.getById(channel.getId());

        assertNotNull(result);
        assertEquals("ТНТ", result.getName());
    }

    @Test
    @DisplayName("Несуществующий канал не находится")
    void getByIdNullChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel result = repository.getById(999L);

        assertNull(result);
    }

    @Test
    @DisplayName("Изменение канала")
    void updateChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel channel = new Channel("ТНТ");
        repository.add(channel);

        Channel updatedChannel = new Channel(channel.getId(),"ТНТ HD");

        boolean result = repository.update(updatedChannel);

        assertTrue(result);
        assertEquals("ТНТ HD", repository.getById(channel.getId()).getName());
    }

    @Test
    @DisplayName("Изменение несуществующего канала")
    void updateNotFoundChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel channel = new Channel(999L, "Несуществующий канал");

        boolean result = repository.update(channel);

        assertFalse(result);
    }

    @Test
    @DisplayName("Удаление канала")
    void deleteChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        Channel channel = new Channel("НТВ");
        repository.add(channel);

        boolean result = repository.delete(channel.getId());

        assertTrue(result);
        assertNull(repository.getById(channel.getId()));
    }

    @Test
    @DisplayName("Удаление несуществующего канала")
    void deleteNotFoundChannel() {
        ChannelRepository repository = new ChannelRepositoryImpl();

        boolean result = repository.delete(999L);

        assertFalse(result);
    }
}
