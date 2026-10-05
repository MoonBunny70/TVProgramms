package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.List;

public interface ChannelRepository {

    List<Channel> getAll();

    Channel getById(Long id);

    boolean add(Channel channel);

    boolean update(Channel channel);

    boolean delete(Long id);
}
