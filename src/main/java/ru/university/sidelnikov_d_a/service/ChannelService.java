package ru.university.sidelnikov_d_a.service;

import ru.university.sidelnikov_d_a.exception.ChannelNotFoundException;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.repository.ChannelRepository;
import ru.university.sidelnikov_d_a.repository.ProgramRepository;
import ru.university.sidelnikov_d_a.validator.ChannelValidator;

import java.util.List;

public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ProgramRepository programRepository;

    public ChannelService(ChannelRepository channelRepository, ProgramRepository programRepository) {
        this.channelRepository = channelRepository;
        this.programRepository = programRepository;
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

    public boolean delete(Long id) {
        Channel channel = getById(id);

        if (channelRepository.getById(id) == null) {
            throw new ChannelNotFoundException(id);
        }

        if (!programRepository.getByChannel(channel).isEmpty()) {
            throw new IllegalStateException(
                    "Нельзя удалить канал, пока он используется в программах"
            );
        }

        return channelRepository.delete(id);
    }
}
