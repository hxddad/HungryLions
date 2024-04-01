import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PortionValueService {
	
	
	private Connection conn = DatabaseConnection.connect("app");

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM Restaurants";
        
        try {
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Restaurant restaurant = new Restaurant(
                        rs.getInt("ID"),
                        rs.getString("RestaurantName"),
                        rs.getString("Location"),
                        rs.getString("Cuisine"),
                        rs.getString("AcceptedPaymentMethod"),
                        rs.getString("DietRestriction"),
                        rs.getString("PriceRange")
                );
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return restaurants;
    }

    public List<String> filterRestaurantsByPriceRange(String priceRange) {
        return getRestaurants().stream()
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
        return getRestaurants().stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(Restaurant::getPriceRange)
                .orElse("Unknown");
    }

    public List<String> getRestaurantsWithLocation() {
        return getRestaurants().stream()
                .map(restaurant -> restaurant.getName() + " - " + restaurant.getLocation())
                .collect(Collectors.toList());
    }
}
