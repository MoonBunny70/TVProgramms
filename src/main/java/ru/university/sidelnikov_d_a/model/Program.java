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

    public Program(String name, Channel channel, Genre genre, DayOfWeek dayOfWeek, LocalTime startTime) {
        this.name = name;
        this.channel = channel;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }
}
