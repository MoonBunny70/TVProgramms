package ru.university.sidelnikov_d_a.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

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
        if (id == null) {
            throw new IllegalArgumentException(
                    "ID программы не может быть null"
            );
        }

        if (id >= nextId) {
            nextId = id + 1;
        }

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

    // Сравнивается по id-нику
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Program program = (Program) o;

        return id != null && Objects.equals(id, (program.id));
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}
