package ru.university.sidelnikov_d_a.repository.jdbc;

import ru.university.sidelnikov_d_a.database.Database;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;
import ru.university.sidelnikov_d_a.repository.ProgramRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramRepositoryJdbc implements ProgramRepository {
    public List<Program> getAll() {
        List<Program> programs = new ArrayList<>();

        String sql = """
                SELECT
                p.id, p.name,
                c.id AS channel_id, c.name AS channel_name,
                g.id AS genre_id,
                g.name AS genre_name,
                p.day_of_week, p.start_time
                FROM programs p
                JOIN channels c ON p.channel_id = c.id
                JOIN genres g ON p.genre_id = g.id
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                Long channelId = resultSet.getLong("channel_id");
                String channelName = resultSet.getString("channel_name");
                Channel channel = new Channel(channelId, channelName);

                Long genreId = resultSet.getLong("genre_id");
                String genreName = resultSet.getString("genre_name");
                Genre genre = new Genre(genreId, genreName);

                DayOfWeek dayOfWeek = DayOfWeek.valueOf(resultSet.getString("day_of_week"));

                LocalTime startTime = resultSet.getObject("start_time", LocalTime.class);

                Program program = new Program(id, name, channel, genre, dayOfWeek, startTime);

                programs.add(program);
            }

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }

        return programs;
    }

    public Program getById(Long id) {
        String sql = "SELECT id, name, channel, genre, FROM programs WHERE id = ?";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long programID = resultSet.getLong("id");
                    String name = resultSet.getString("name");


                    // Program program = new Program(id, name, );

                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения программы", e);
        }

        return null;
    }

    public List<Program> getByChannel(Channel channel) {
        return null;
    }

    public List<Program> getByGenre(Genre genre) {
        return null;
    }

    public Program add(Program program) {
        String sql = "INSERT INTO programs (name, ) VALUES (?, ?, ?, ?,) RETURNING id";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, program.getName());
            // statement.;
            // statement.;
            // statement.;

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    // Поля
                    return new Program(id, program.getName(), program.getChannel(), program.getGenre(), program.getDayOfWeek(), program.getStartTime());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }

        return null;
    }

    public Program update(Program program) {
        String sql = """
                UPDATE program
                SET name, 
                WHERE id = ?
                RETURNING id
                """;

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            // statement.;
            // statement.;
            // statement.;
            // statement.;

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления программы", e);
        }

        return null;
    }

    public Program delete(Long id) {
        String sql = """
                DELETE FROM programs
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
                    // Поля

                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления ", e);
        }

        return null;
    }
}
