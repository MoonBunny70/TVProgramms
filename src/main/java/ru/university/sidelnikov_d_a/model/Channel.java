package ru.university.sidelnikov_d_a.model;

import java.util.Objects;

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

    // Сравнивается по id-нику
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Channel channel = (Channel) o;

        return id != null && Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
