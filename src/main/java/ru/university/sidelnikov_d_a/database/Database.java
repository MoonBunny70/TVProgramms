package ru.university.sidelnikov_d_a.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

public class Database {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_USER");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection getConnection() {
        return null;
    }

    public static void createTables() {

    }
}
