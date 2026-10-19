package ru.university.sidelnikov_d_a.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.university.sidelnikov_d_a.model.Channel;
import ru.university.sidelnikov_d_a.model.Genre;

import static org.junit.jupiter.api.Assertions.*;

public class GenreRepositoryImplTest {
    @Test
    @DisplayName("Добавление жанра")
    void addGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre genre = new Genre("Новости");

        boolean result = genreRepository.add(genre);

        assertTrue(result);
        assertEquals(1, genreRepository.getAll().size());
        assertEquals("Новости", genreRepository.getAll().get(0).getName());
    }

    @Test
    @DisplayName("Существующий жанр находится")
    void getByIdGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre genre = new Genre("Новости");
        genreRepository.add(genre);

        Genre result = genreRepository.getById(genre.getId());

        assertNotNull(result);
        assertEquals("Новости", result.getName());
    }


    @Test
    @DisplayName("Несуществующий жанр не находится")
    void getByIdNullGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre result = genreRepository.getById(999L);

        assertNull(result);
    }


    @Test
    @DisplayName("Изменение жанра")
    void updateGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre genre = new Genre("Новости");
        genreRepository.add(genre);

        Genre updatedGenre = new Genre(genre.getId(),"Комедия");

        boolean result = genreRepository.update(updatedGenre);

        assertTrue(result);
        assertEquals("Комедия", genreRepository.getById(genre.getId()).getName());
    }

    @Test
    @DisplayName("Изменение несуществующего жанра")
    void updateNotFoundGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre genre = new Genre(999L, "Несуществующий жанр");

        boolean result = genreRepository.update(genre);

        assertFalse(result);
    }

    @Test
    @DisplayName("Удаление жанра")
    void deleteCGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl();

        Genre genre = new Genre("Комедия");
        genreRepository.add(genre);

        boolean result = genreRepository.delete(genre.getId());

        assertTrue(result);
        assertNull(genreRepository.getById(genre.getId()));
    }

    @Test
    @DisplayName("Удаление несуществующего жанра")
    void deleteNotFoundGenre() {
        GenreRepository genreRepository = new GenreRepositoryImpl() ;

        boolean result = genreRepository.delete(999L);

        assertFalse(result);
    }
}
