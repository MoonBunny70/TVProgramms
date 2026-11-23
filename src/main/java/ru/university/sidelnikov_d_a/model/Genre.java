package ru.university.sidelnikov_d_a.model;

import java.util.Objects;

public class Genre {
    private static Long nextID = 1L;

    private Long id;
    private String name;

    public Genre(String name) {
        this.id = nextID++;
        this.name = name;
    }

    public Genre(Long id, String name) {
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

    // Сравнивается по id-нику
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Genre genre = (Genre) o;

        return id != null && Objects.equals(id, genre.id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}
