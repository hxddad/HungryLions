import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.manipulation.Filter;

public class FavoritesTest {
    Favourites favourites;
    @BeforeEach
    void init() {
        favorites = new Favourites();
    }

    @Test
    void add_favourites() {
        favourites.addFavorites("Tim Hortons");
        assertEquals(true, favourites.getFavorites().contains("Tim Hortons"));
    }

    @Test
    void remove_favourites() {
        favourites.addFavorites("Tim Hortons");
        assertEquals(true, favourites.removeFavorites("Tim Hortons"));
    }

    @Test
    void remove_favourties2() {
        favourties.addFavorites("Osmows");
        assertEquals(false, favourites.removeFavorites("Tim Hortons"));
    }
}
