package ru.university.sidelnikov_d_a.model;

public class Genre {
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

    public Genre(String name) {
        this.name = name;
    }
}
