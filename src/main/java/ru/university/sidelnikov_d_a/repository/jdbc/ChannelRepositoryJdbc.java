package ru.university.sidelnikov_d_a.repository.jdbc;

import ru.university.sidelnikov_d_a.database.Database;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.repository.ChannelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChannelRepositoryJdbc implements ChannelRepository {
    public List<Channel> getAll() {
        List<Channel> channels = new ArrayList<>();

        String sql = "SELECT id, name FROM channels";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                Channel channel = new Channel(id, name);

                channels.add(channel);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения каналов", e);
        }

        return channels;
    }

    public Channel getById(Long id) {
        String sql = "SELECT id, name FROM channels WHERE id = ?";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long channelID = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Channel(channelID, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения канала", e);
        }

        return null;
    }

    public Channel add(Channel channel) {
        String sql = "INSERT INTO channels (name) VALUES (?) RETURNING id";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, channel.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    return new Channel(id, channel.getName());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления канала", e);
        }

        return null;
    }

    public Channel update(Channel channel) {
        String sql = """
                UPDATE channels
                SET name = ?
                WHERE id = ?
                RETURNING id, name
                """;

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(2, channel.getId());
            statement.setString(1, channel.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Channel(id, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления канала", e);
        }

        return null;
    }

    public Channel delete(Long id) {
        String sql = """
                DELETE FROM channels
                WHERE id = ?
                RETURNING id, name
                """;

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long channelId = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Channel(channelId, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления канала", e);
        }

        return null;
    }
}
