package ru.university.sidelnikov_d_a;

import ru.university.sidelnikov_d_a.controller.TVController;
import ru.university.sidelnikov_d_a.database.Database;
import ru.university.sidelnikov_d_a.repository.*;
import ru.university.sidelnikov_d_a.repository.jdbc.ChannelRepositoryJdbc;
import ru.university.sidelnikov_d_a.repository.jdbc.GenreRepositoryJdbc;
import ru.university.sidelnikov_d_a.repository.jdbc.ProgramRepositoryJdbc;
import ru.university.sidelnikov_d_a.service.*;
import ru.university.sidelnikov_d_a.view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Database.createTables();

        // Подключение к базе данных
        try (Connection connection = Database.getConnection()) {
            System.out.println("Подключение к базе данных успешно!");
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных.");
            e.printStackTrace();
            return;
        }

        // Репозитории
        ChannelRepository channelRepository = new ChannelRepositoryJdbc();
        GenreRepository genreRepository = new GenreRepositoryJdbc();
        ProgramRepository programRepository = new ProgramRepositoryJdbc();

        // Сервисы
        ChannelService channelService = new ChannelService(
                        channelRepository,
                        programRepository
                );
        GenreService genreService = new GenreService(
                        genreRepository,
                        programRepository
                );
        ProgramService programService = new ProgramService(
                        channelRepository,
                        genreRepository,
                        programRepository
                );

        // Контроллер
        TVController controller = new TVController(
                        channelService,
                        genreService,
                        programService
                );

        ConsoleView consoleView = new ConsoleView(controller);
        consoleView.start();
    }
}