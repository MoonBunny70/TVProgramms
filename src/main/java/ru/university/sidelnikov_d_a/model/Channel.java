package ru.university.sidelnikov_d_a.model;

public class Channel {
    private static Long nextId = 1L;

    private Long id;
    private String name;

    public Channel(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
