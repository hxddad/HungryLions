import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PortionValueServiceTest {

    private PortionValueService service;

    @BeforeEach
    void setUp() {
        service = new PortionValueService();
    }

    @Test
    void testFilterRestaurantsByPriceRangeHigh() {
        List<String> highRangeRestaurants = service.filterRestaurantsByPriceRange("high");
        assertNotNull(highRangeRestaurants);
        assertFalse(highRangeRestaurants.isEmpty());
        assertTrue(highRangeRestaurants.contains("Chop'd & Wrap'd - Central Square (CSQ)"));
    }

    @Test
    void testInterpretPriceRangeAndPortion() {
        assertEquals("Low price range and good food portion", service.interpretPriceRangeAndPortion("low"));
        assertEquals("Medium price range and good food portion", service.interpretPriceRangeAndPortion("medium"));
        assertEquals("High price range and moderate food portion", service.interpretPriceRangeAndPortion("high"));
        assertEquals("Expensive price range and moderate to low food portion", service.interpretPriceRangeAndPortion("expensive"));
        assertEquals("Unknown price range", service.interpretPriceRangeAndPortion("nonexistent"));
    }

    @Test
    void testGetPriceRangeByName() {
        assertEquals("high", service.getPriceRangeByName("Chop'd & Wrap'd"));
        assertEquals("Unknown", service.getPriceRangeByName("Nonexistent Restaurant"));
    }

    @Test
    void testGetRestaurantsWithLocation() {
        List<String> restaurantsWithLocation = service.getRestaurantsWithLocation();
        assertNotNull(restaurantsWithLocation);
        assertFalse(restaurantsWithLocation.isEmpty());
        assertTrue(restaurantsWithLocation.contains("Chop'd & Wrap'd - Central Square (CSQ)"));
    }
    
    @Test
    void testFilterRestaurantsByNonexistentPriceRange() {
        List<String> restaurants = service.filterRestaurantsByPriceRange("nonexistent");
        assertTrue(restaurants.isEmpty(), "List should be empty for a nonexistent price range");
    }

    @Test
    void testInterpretPriceRangeAndPortionForInvalidInput() {
        String interpretation = service.interpretPriceRangeAndPortion("");
        assertEquals("Unknown price range", interpretation, "Empty string should return 'Unknown price range'");
    }

    @Test
    void testGetPriceRangeByNameForEmptyName() {
        String priceRange = service.getPriceRangeByName("");
        assertEquals("Unknown", priceRange, "Empty restaurant name should return 'Unknown'");
    }
    @Test
    void testGetRestaurantsWithLocationSizeAndContents() {
        List<String> restaurants = service.getRestaurantsWithLocation();
        assertEquals(13, restaurants.size(), "Should match the exact number of initialized restaurants");

        assertTrue(restaurants.contains("Chop'd & Wrap'd - Central Square (CSQ)"), "List should contain specific restaurant and location");
    }
    
    @Test
    void testInterpretPriceRangeAndPortionWithUnexpectedValues() {
        assertEquals("Unknown price range", service.interpretPriceRangeAndPortion("free"), "Unexpected price ranges should return 'Unknown price range'");
        assertEquals("Unknown price range", service.interpretPriceRangeAndPortion(null), "Null input should return 'Unknown price range'");
    }
    @Test
    void testConsistencyOfPriceRangeByName() {
        String firstCall = service.getPriceRangeByName("Chop'd & Wrap'd");
        String secondCall = service.getPriceRangeByName("Chop'd & Wrap'd");
        assertEquals(firstCall, secondCall, "Multiple calls with the same input should return consistent results");
    }


}
