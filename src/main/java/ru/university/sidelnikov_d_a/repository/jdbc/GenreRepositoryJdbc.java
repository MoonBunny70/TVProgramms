package ru.university.sidelnikov_d_a.repository.jdbc;

import ru.university.sidelnikov_d_a.database.Database;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.repository.GenreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryJdbc implements GenreRepository {
    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>();

        String sql = "SELECT id, name FROM genres";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                Genre genre = new Genre(id, name);
                genres.add(genre);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения жанров", e);
        }

        return genres;
    }

    public Genre getById(Long id) {
        String sql = "SELECT id, name FROM genres WHERE id = ?";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long genreID = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Genre(genreID, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения жанра", e);
        }

        return null;
    }

    public Genre add(Genre genre) {
        String sql = "INSERT INTO genres (name) VALUES (?) RETURNING id";

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, genre.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    return new Genre(id, genre.getName());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления жанра", e);
        }

        return null;
    }

    public Genre update(Genre genre) {
        String sql = """
                UPDATE genres
                SET name = ?
                WHERE id = ?
                RETURNING id, name
                """;

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(2, genre.getId());
            statement.setString(1, genre.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Genre(id, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления жанра", e);
        }

        return null;
    }

    public Genre delete(Long id) {
        String sql = """
                DELETE FROM genres
                WHERE id = ?
                RETURNING id, name
                """;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long genreId = resultSet.getLong("id");
                    String name = resultSet.getString("name");

                    return new Genre(genreId, name);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления жанра", e);
        }

        return null;
    }
}
