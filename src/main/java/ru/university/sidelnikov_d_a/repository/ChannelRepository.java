package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.List;

public interface ChannelRepository {

    List<Channel> getAll();

    Channel getById(Long id);

    void add(Channel channel);

    void delete(Long id);
}
