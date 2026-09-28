package ru.university.sidelnikov_d_a.model;

public class Channel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel(String name) {
        this.name = name;
    }
}
