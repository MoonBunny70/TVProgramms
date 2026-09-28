package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelRepositoryImpl implements ChannelRepository {
    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getAll() {
        return channels;
    }

    public Channel getById(Long id) {
        for (Channel channel : channels) {
            if (channel.getId() == id) {
                return channel;
            }
        }
        return null;
    }

    public void add(Channel channel) {
        channels.add(channel);
    }

    public void delete(Long id) {
        Channel deleted = getById(id);

        if (deleted != null) {
            channels.remove(deleted);
        }
    }

    public void update(Channel channel) {
        Channel existingChannel = getById(channel.getId());

        if (existingChannel != null) {
            existingChannel.setName(channel.getName());
        }
    }
}
