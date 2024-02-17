import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PortionValueService {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    static {
    	restaurants.add(new Restaurant(14, "Chop'd & Wrap'd", "Central Square (CSQ)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
    	restaurants.add(new Restaurant(15, "Chop'd & Wrap'd", "Dahdaleh Building (DB)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
    	restaurants.add(new Restaurant(16, "Chop'd & Wrap'd", "Winters College (WC)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
    	restaurants.add(new Restaurant(17, "Chungchun Rice Hot Dog", "The Quad", "Korean", "Cash, Credit Card, Debit Card", "Halal (H), Vegan (V), Vegetarian (VG)", "low"));
    	restaurants.add(new Restaurant(18, "Country Style (Kosher Deli)", "Winters College (WC)", "Mediterranean", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Kosher (K), Vegan (V), Vegetarian (VG)", "high"));
    	restaurants.add(new Restaurant(19, "Create Stir Fry", "Stong College (SC)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegetarian (VG)", "medium"));
    	restaurants.add(new Restaurant(20, "Create Stir Fry", "Central Square (CSQ)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "medium"));
    	restaurants.add(new Restaurant(21, "Create Stir Fry", "Winters College (WC)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegetarian (VG)", "medium"));
    	restaurants.add(new Restaurant(22, "Crepe Delicious", "The Quad", "French", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "medium"));
    	restaurants.add(new Restaurant(23, "Cucina Italian Cafe", "York Lanes Mall", "Italian", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Vegan (V), Vegetarian (VG)", "high"));
    	restaurants.add(new Restaurant(24, "Executive Dining Room", "Schulich Dining", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), No Gluten (NG), Vegan (V), Vegetarian (VG)", "expensive"));
    	restaurants.add(new Restaurant(25, "Fat Bastard Burrito", "The Quad", "Mexican", "Cash, Credit Card, Debit Card", "No Gluten (NG), Vegan (V), Vegetarian (VG)", "low"));
    	restaurants.add(new Restaurant(26, "Galito's Flamed Grilled Chicken", "The Quad", "South African", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), Vegan (V)", "medium"));

    }

    public List<String> filterRestaurantsByPriceRange(String priceRange) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getPriceRange().equalsIgnoreCase(priceRange))
                .map(Restaurant::getName)
                .collect(Collectors.toList());
    }

    public String interpretPriceRangeAndPortion(String priceRange) {
        switch (priceRange) {
            case "low":
                return "Low price range and good food portion";
            case "medium":
                return "Medium price range and good food portion";
            case "high":
                return "High price range and moderate food portion";
            case "expensive":
                return "Expensive price range and moderate to low food portion";
            default:
                return "Unknown price range";
        }
    }

    public String getPriceRangeByName(String name) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(Restaurant::getPriceRange)
                .orElse("Unknown");
    }

    public List<String> getRestaurantsWithLocation() {
        return restaurants.stream()
                .map(restaurant -> restaurant.getName() + " - " + restaurant.getLocation())
                .collect(Collectors.toList());
    }
}
