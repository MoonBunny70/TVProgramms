package ru.university.sidelnikov_d_a.service;

import ru.university.sidelnikov_d_a.exception.ChannelNotFoundException;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.repository.ChannelRepository;
import ru.university.sidelnikov_d_a.validator.ChannelValidator;

import java.util.List;

public class ChannelService {
    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getAll() {
        return channelRepository.getAll();
    }

    public Channel getById(Long id) {
        Channel channel = channelRepository.getById(id);

        if (channel == null) {
            throw new ChannelNotFoundException(id);
        }

        return channel;
    }

    public boolean add(Channel channel) {
        ChannelValidator.validate(channel);

        return channelRepository.add(channel);
    }

    public boolean update(Channel channel) {
        ChannelValidator.validate(channel);

        if (channelRepository.getById(channel.getId()) == null) {
            throw new ChannelNotFoundException(channel.getId());
        }

        return channelRepository.update(channel);
    }

    public void delete(Long id) {
        if (channelRepository.getById(id) == null) {
            throw new ChannelNotFoundException(id);
        }

        channelRepository.delete(id);
    }
}
