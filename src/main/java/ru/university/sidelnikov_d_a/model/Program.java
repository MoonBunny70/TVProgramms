package ru.university.sidelnikov_d_a.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Program {
    private static Long nextId = 1L;

    private Long id;
    private String name;
    private Channel channel;
    private Genre genre;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;

    public Program(String name, Channel channel, Genre genre, DayOfWeek dayOfWeek, LocalTime startTime) {
        this.id = nextId++;
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    public Program(Long id, String name, Channel channel, Genre genre, DayOfWeek dayOfWeek, LocalTime startTime) {
        this.id = id;
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Channel getChannel() {
        return channel;
    }
    public Genre getGenre() {
        return genre;
    }
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
