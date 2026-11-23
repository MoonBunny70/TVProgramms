package ru.university.sidelnikov_d_a.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() {
        createChannelTable();
        createGenreTable();
        createProgramTable();
    }

    public static void createChannelTable() {
        String channelSql = """
                CREATE TABLE IF NOT EXISTS channels (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(20) NOT NULL
                )
                """;

        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {
            statement.execute(channelSql);
            System.out.println("Таблица каналов создана!");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы каналов", e);
        }
    }

    public static void createGenreTable() {
        String genreSql = """
                CREATE TABLE IF NOT EXISTS genres (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(30) NOT NULL
                )
                """;

        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {

            statement.execute(genreSql);
            System.out.println("Таблица жанров создана!");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы жанров", e);
        }
    }

    public static void createProgramTable() {
        String programsSql = """
        CREATE TABLE IF NOT EXISTS programs (
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(30) NOT NULL,
            channel_id BIGINT NOT NULL REFERENCES channels(id),
            genre_id BIGINT NOT NULL REFERENCES genres(id),
            day_of_week VARCHAR(20) NOT NULL,
            start_time TIME NOT NULL
        )
        """;

        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {

            statement.execute(programsSql);
            System.out.println("Таблица программ создана!");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы программ", e);
        }
    }
}
