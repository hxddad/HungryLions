import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodSearchProgram {

    public FoodSearchProgram() {
    }

    private List<Restaurant> fetchRestaurantsFromDatabase() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM Restaurants"; 

        try (Connection conn = DatabaseConnection.connect("app");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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

    
    public List<Restaurant> searchFoodSpots(List<String> desiredCuisines, boolean isHalal, boolean isGlutenFree, boolean isVegan, boolean isVegetarian, String searchText) {
        List<Restaurant> restaurants = fetchRestaurantsFromDatabase();
        List<Restaurant> results = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
        	DietaryFilter filter = new DietaryFilter(restaurant);
            if ((desiredCuisines.contains("All Cuisine") || desiredCuisines.contains(restaurant.getCuisine())) &&
                    (!isHalal || filter.isHalal()) &&
                    (!isGlutenFree || filter.isGlutenFree()) &&
                    (!isVegan || filter.isVegan()) &&
                    (!isVegetarian || filter.isVegetarian()) &&
                    restaurant.getName().toLowerCase().contains(searchText.toLowerCase())) {
                results.add(restaurant);
            }
        }
        return results;
    }

    
    public List<String> fetchDistinctCuisines() {
        List<String> cuisines = new ArrayList<>();
        String sql = "SELECT DISTINCT Cuisine FROM Restaurants ORDER BY Cuisine"; 

        try (Connection conn = DatabaseConnection.connect("app");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                cuisines.add(rs.getString("Cuisine"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cuisines;
    }
}
