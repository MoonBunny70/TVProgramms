package ru.university.sidelnikov_d_a.repository;

import ru.university.sidelnikov_d_a.model.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelRepositoryImpl implements ChannelRepository {
    private List<Channel> channels = new ArrayList<>();

    @Override
    public List<Channel> getAll() {
        return channels;
    }

    @Override
    public Channel getById(Long id) {
        for (Channel channel : channels) {
            if (channel.getId().equals(id)) {
                return channel;
            }
        }
        return null;
    }

    @Override
    public boolean add(Channel channel) {
        channels.add(channel);
        return true;
    }

    @Override
    public boolean update(Channel channel) {
        Channel existingChannel = getById(channel.getId());

        if (existingChannel != null) {
            existingChannel.setName(channel.getName());
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        Channel channel = getById(id);

        if (channel != null) {
            channels.remove(channel);
            return true;
        }

        return false;
    }
}
