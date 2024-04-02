//abhiroop add comments
import java.sql.*;

public class Reviews {
	private Connection connection;
	private Statement statement;
	private RestaurantIdtoName Restaurants = new RestaurantIdtoName();

	
	public Reviews() {
		try {
			connection = DatabaseConnection.connect("review");
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
	}


	public String getReviews(String selectedRestaurant) throws SQLException {
	    PreparedStatement ps = connection.prepareStatement("SELECT foodQuality, review_text, service, atmosphere, valueForMoney, timesGone, numberOfItemsAte FROM reviews WHERE restaurant_id = ?");
	    ps.setString(1, Restaurants.getName(selectedRestaurant));
	    ResultSet rs = ps.executeQuery();
	    StringBuilder reviews = new StringBuilder();
	    int i =0;
	    while (rs.next()) {
	    	i++;
	        // Retrieve each column value separately
	        int foodQuality = rs.getInt("foodQuality");
	        String reviewText = rs.getString("review_text");
	        int service = rs.getInt("service");
	        int atmosphere = rs.getInt("atmosphere");
	        int valueForMoney = rs.getInt("valueForMoney");
	        int timesGone = rs.getInt("timesGone");
	        int numberOfItemsAte = rs.getInt("numberOfItemsAte");
	        
	        // Append the retrieved values to the StringBuilder
	        reviews.append("Review ").append(i).append(":\n\n").append("Food Quality: ").append(foodQuality)
	               .append("\nReview Text: ").append(reviewText).append("\nService: ").append(service)
	               .append("\nAtmosphere: ").append(atmosphere).append("\nValue for Money: ").append(valueForMoney)
	               .append("\nTimes Gone: ").append(timesGone).append("\nNumber of Items Ate: ").append(numberOfItemsAte)
	               .append("\n\n");
	    }
	    rs.close();
	    ps.close();
	    return reviews.toString().trim();
	}

	
	public void close() {
	    try {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    } catch (SQLException e) {
	        System.out.println("Error closing the database connection");
	        e.printStackTrace();
	    }
	}

	
}