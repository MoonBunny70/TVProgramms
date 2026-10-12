package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChannelRepositoryImpl implements ChannelRepository {
    private List<Channel> channels = new ArrayList<>();

    @Override
    public final List<Channel> getAll() {
        return channels;
    }

    @Override
    public Channel getById(Long id) {
        for (Channel channel : channels) {
            if (Objects.equals(channel.getId(), id)) {
                return channel;
            }
        }
        return null;
    }

    @Override
    public boolean add(Channel channel) {
        if (channel == null || channel.getId() == null) {
            return false;
        }

        // Дублирование ID
        if (getById(channel.getId()) != null) {
            return false;
        }

        channels.add(channel);
        return true;
    }

    @Override
    public boolean update(Channel channel) {
        Channel existingChannel = getById(channel.getId());

        if (existingChannel == null) {
            return false;
        }

        existingChannel.setName(channel.getName());

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Channel channel = getById(id);

        if (channel == null) {
            return false;
        }

        channels.remove(channel);

        return true;
    }
}
