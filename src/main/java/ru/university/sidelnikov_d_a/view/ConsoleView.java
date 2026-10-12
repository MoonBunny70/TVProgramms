package ru.university.sidelnikov_d_a.view;

import ru.university.sidelnikov_d_a.controller.TVController;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;
import ru.university.sidelnikov_d_a.model.Program;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final TVController controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(TVController controller) {
        this.controller = controller;
    }

    public void start() {
        boolean running = true;

        while (running) {
            int channelsCnt = controller.getChannelCount();
            int genresCnt = controller.getGenreCount();
            int programsCnt = controller.getProgramCount();

            System.out.println("\n=== Телепрограмма ===");
            System.out.println("1. Программы (" + programsCnt + ")");
            System.out.println("2. Телеканалы (" + channelsCnt + ")");
            System.out.println("3. Жанры (" + genresCnt + ")");
            System.out.println("0. Выход (X)");

            String choice = readLine("Выберите раздел: ");
            System.out.println();

            try {
                switch (choice) {
                    case "1" -> showProgramsMenu();
                    case "2" -> showChannelsMenu();
                    case "3" -> showGenresMenu();
                    case "0" -> running = false;
                    default -> System.out.println("Неизвестная команда.");
                }
            } catch (RuntimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Программа завершена. Спасибо, что пользуетесь нами!");
    }


    // Программы
    public void showProgramsMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Программы ===");
            System.out.println("1. Показать все программы");
            System.out.println("2. Добавить программу");
            System.out.println("3. Редактировать программу");
            System.out.println("4. Удалить программу");
            System.out.println("0. Назад");

            String choice = readLine("Выберите действие: ");
            System.out.println();

            switch (choice) {
                case "1" -> showAllPrograms();
                case "2" -> addProgram();
                case "3" -> editProgram();
                case "4" -> deleteProgram();
                case "0" -> back = true;
                default -> System.out.println("[Система]: Неизвестная команда.");
            }
        }
    }

    private void showAllPrograms() {
        List<Program> programs = controller.getAllPrograms();

        if (programs.isEmpty()) {
            System.out.println("[Система]: Программ пока нет.");
            return;
        }

        System.out.println("[");
        for (Program program : programs) {
            System.out.println(
                    "   {\n       ID: " + program.getId()
                            + "\n       Название: " + program.getName()
                            + "\n       Канал: " + program.getChannel().getName()
                            + "\n       Жанр: " + program.getGenre().getName()
                            + "\n       День: " + program.getDayOfWeek()
                            + "\n       Время: " + program.getStartTime() + "\n   }"
            );
        }

        System.out.println("]");
    }

    private void addProgram() {
        System.out.println("\n=== Добавление программы ===");
        System.out.println();

        showAllChannels();
        System.out.println();

        Long channelId = readLong("Введите ID телеканала: ");
        Channel channel = controller.getChannelById(channelId);
        System.out.println();

        showAllGenres();
        System.out.println();

        Long genreId = readLong("Введите ID жанра: ");
        Genre genre = controller.getGenreById(genreId);
        System.out.println();

        String name = readLine("Название программы: ");
        System.out.println();
        DayOfWeek day = readDayOfWeek();
        System.out.println();
        LocalTime time = readTime();

        boolean added = controller.addProgram(new Program(name, channel, genre, day, time));

        if (added) {
            System.out.println("[Система]: Программа добавлена!");
        } else {
            System.out.println("[Система]: Не удалось добавить программу. Возможно, такой ID уже существует.");
        }
    }

    private void editProgram() {
        System.out.println("\n=== Редактирование программы ===");
        showAllPrograms();

        Long id = readLong("Введите ID программы: ");
        String name = readLine("Новое название: ");

        System.out.println();
        showAllChannels();

        Long channelId = readLong("Новый ID телеканала: ");
        Channel channel = controller.getChannelById(channelId);

        showAllGenres();

        Long genreId = readLong("Новый ID жанра: ");
        Genre genre = controller.getGenreById(genreId);
        DayOfWeek day = readDayOfWeek();
        LocalTime time = readTime();

        boolean updated = controller.updateProgram(
                new Program(id, name, channel, genre, day, time)
        );

        if (updated) {
            System.out.println("[Система]: Программа обновлена!");
        } else {
            System.out.println("[Система]: Не удалось обновить программу.");
        }
    }

    private void deleteProgram() {
        showAllPrograms();

        Long id = readLong("Введите ID программы для удаления: ");

        boolean deleted = controller.deleteProgram(id);

        if (deleted) {
            System.out.println("[Система]: Программа удалена!");
        } else {
            System.out.println("[Система]: Не удалось удалить программу.");
        }
    }


    // Каналы
    private void showChannelsMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Телеканалы ===");
            System.out.println("1. Показать все каналы");
            System.out.println("2. Добавить канал");
            System.out.println("3. Редактировать канал");
            System.out.println("4. Удалить канал");
            System.out.println("0. Назад");

            String choice = readLine("Выберите действие: ");
            System.out.println();

            switch (choice) {
                case "1" -> showAllChannels();
                case "2" -> addChannel();
                case "3" -> editChannel();
                case "4" -> deleteChannel();
                case "0" -> back = true;
                default -> System.out.println("[Система]: Неизвестная команда.");
            }
        }
    }

    private void showAllChannels() {
        List<Channel> channels = controller.getAllChannels();

        if (channels.isEmpty()) {
            System.out.println("[Система]: Телеканалов пока нет.");
            return;
        }

        System.out.println("[");
        for (Channel channel : channels) {
            System.out.println(
                    "   {\n       ID: " + channel.getId()
                            + "\n       Название: " + channel.getName() + "\n   }"
            );
        }
        System.out.println("]");
    }

    private void addChannel() {
        String name = readLine("Название телеканала: ");

        System.out.println();

        boolean added = controller.addChannel(new Channel(name));

        if (added) {
            System.out.println("[Система]: Телеканал добавлен!");
        } else {
            System.out.println("[Система]: Не удалось добавить телеканал. Возможно, такой ID уже существует.");
        }
    }

    private void editChannel() {
        showAllChannels();

        Long id = readLong("Введите ID телеканала: ");
        String name = readLine("Новое название: ");

        System.out.println();

        boolean updated = controller.updateChannel(new Channel(id, name));

        if (updated) {
            System.out.println("[Система]: Телеканал обновлён!");
        } else {
            System.out.println("[Система]: Не удалось обновить телеканал.");
        }
    }

    private void deleteChannel() {
        showAllChannels();

        Long id = readLong("Введите ID телеканала для удаления: ");

        System.out.println();

        boolean deleted = controller.deleteChannel(id);

        if (deleted) {
            System.out.println("[Система]: Телеканал удалён!");
        } else {
            System.out.println("[Система]: Не удалось удалить телеканал.");
        }
    }


    // Жанры
    private void showGenresMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Жанры ===");
            System.out.println("1. Показать все жанры");
            System.out.println("2. Добавить жанр");
            System.out.println("3. Редактировать жанр");
            System.out.println("4. Удалить жанр");
            System.out.println("0. Назад");

            String choice = readLine("Выберите действие: ");
            System.out.println();

            switch (choice) {
                case "1" -> showAllGenres();
                case "2" -> addGenre();
                case "3" -> editGenre();
                case "4" -> deleteGenre();
                case "0" -> back = true;
                default -> System.out.println("[Система]: Неизвестная команда.");
            }
        }
    }

    private void showAllGenres() {
        List<Genre> genres = controller.getAllGenres();

        if (genres.isEmpty()) {
            System.out.println("[Система]: Жанров пока нет.");
            return;
        }

        System.out.println("[");
        for (Genre genre : genres) {
            System.out.println(
                    "   {\n       ID: " + genre.getId()
                            + "\n       Название: " + genre.getName() + "\n   }"
            );
        }
        System.out.println("]");
    }

    private void addGenre() {
        String name = readLine("Название жанра: ");

        System.out.println();

        boolean added = controller.addGenre(new Genre(name));

        if (added) {
            System.out.println("[Система]: Жанр добавлен!");
        } else {
            System.out.println("[Система]: Не удалось добавить жанр.");
        }

    }

    private void editGenre() {
        showAllGenres();

        Long id = readLong("Введите ID жанра: ");
        String name = readLine("Новое название: ");

        System.out.println();

        boolean updated = controller.updateGenre(new Genre(id, name));

        if (updated) {
            System.out.println("[Система]: Жанр обновлён!");
        } else {
            System.out.println("[Система]: Не удалось обновить жанр.");
        }
    }

    private void deleteGenre() {
        showAllGenres();

        Long id = readLong("Введите ID жанра для удаления: ");

        boolean deleted = controller.deleteGenre(id);

        if (deleted) {
            System.out.println("[Система]: Жанр удалён!");
        } else {
            System.out.println("[Система]: Не удалось удалить жанр.");
        }
    }


    // Ввод данных
    private String readLine(String message) {
        System.out.println("=====================");
        System.out.print("[Система]: " + message);
        return scanner.nextLine().trim();
    }

    private Long readLong(String message) {
        while (true) {
            try {
                return Long.parseLong(readLine(message));
            } catch (NumberFormatException e) {
                System.out.println("[Система]: Введите целое число.");
            }
        }
    }

    private DayOfWeek readDayOfWeek() {
        while (true) {
            System.out.println(" ======== День недели ========");
            System.out.println("1. Понедельник");
            System.out.println("2. Вторник");
            System.out.println("3. Среда");
            System.out.println("4. Четверг");
            System.out.println("5. Пятница");
            System.out.println("6. Суббота");
            System.out.println("7. Воскресенье");
            System.out.println();
            Long number = readLong("Выберите день (1-7): ");

            if (number >= 1 && number <= 7) {
                return DayOfWeek.of(number.intValue());
            }

            System.out.println("[Система]: Число должно быть от 1 до 7.");
        }
    }

    private LocalTime readTime() {
        while (true) {
            String input = readLine("Время начала (ЧЧ:ММ, например 09:30): ");

            try {
                return LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("[Система]: Неверный формат времени. Пример: 09:30.");
            }
        }
    }
}
