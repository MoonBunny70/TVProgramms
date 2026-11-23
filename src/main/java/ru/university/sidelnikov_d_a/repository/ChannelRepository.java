package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.List;

public interface ChannelRepository {

    List<Channel> getAll();

    Channel getById(Long id);

    Channel add(Channel channel);

    Channel update(Channel channel);

    Channel delete(Long id);
}
