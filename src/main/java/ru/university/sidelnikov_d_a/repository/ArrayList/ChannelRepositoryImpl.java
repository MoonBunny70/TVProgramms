package ru.university.sidelnikov_d_a.repository.ArrayList;

import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.repository.ChannelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChannelRepositoryImpl {
    private List<Channel> channels = new ArrayList<>();

    public final List<Channel> getAll() {
        return channels;
    }

    public Channel getById(Long id) {
        for (Channel channel : channels) {
            if (Objects.equals(channel.getId(), id)) {
                return channel;
            }
        }
        return null;
    }

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

    public boolean update(Channel channel) {
        Channel existingChannel = getById(channel.getId());

        if (existingChannel == null) {
            return false;
        }

        existingChannel.setName(channel.getName());

        return true;
    }

    public boolean delete(Long id) {
        Channel channel = getById(id);

        if (channel == null) {
            return false;
        }

        channels.remove(channel);

        return true;
    }
}
