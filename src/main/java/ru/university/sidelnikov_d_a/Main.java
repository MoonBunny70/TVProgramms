package ru.university.sidelnikov_d_a;

import ru.university.sidelnikov_d_a.controller.TVController;
import ru.university.sidelnikov_d_a.repository.*;
import ru.university.sidelnikov_d_a.service.*;
import ru.university.sidelnikov_d_a.view.ConsoleView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChannelRepository channelRepository = new ChannelRepositoryImpl();
        GenreRepository genreRepository = new GenreRepositoryImpl();
        ProgramRepository programRepository = new ProgramRepositoryImpl();

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

        TVController controller = new TVController(
                        channelService,
                        genreService,
                        programService
                );

        ConsoleView consoleView = new ConsoleView(controller);
        consoleView.start();
    }
}