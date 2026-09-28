package ru.university.sidelnikov_d_a.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Program {
    private Long id;
    private String name;
    private Channel channel;
    private Genre genre;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;

    public Program(String name, Channel channel, Genre genre, DayOfWeek dayOfWeek, LocalTime startTime) {
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }
}
