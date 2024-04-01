

import java.sql.*;

public class RestaurantIdtoName {
	private Connection connection;
	private Statement statement;
	
	
	public RestaurantIdtoName() {
		try {
			connection = DatabaseConnection.connect("app");
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
	}

//	public String getReviews(String selectedRestaurant) throws SQLException {
//	    PreparedStatement ps = connection.prepareStatement("SELECT user_id, foodQuality, review_text, service, atmosphere, valueForMoney, timesGone, numberOfItemsAte FROM reviews WHERE restaurant_id = ?");
//	    ps.setString(1, selectedRestaurant);
//	    ResultSet rs = ps.executeQuery();
//	    StringBuilder reviews = new StringBuilder();
//	    while (rs.next()) {
//	    	reviews.append(rs.getString("user_id, foodQuality, review_text, service, atmosphere, valueForMoney, timesGone, numberOfItemsAte")).append("\n");
//	    }
//	    rs.close();
//	    ps.close();
//	    return reviews.toString().trim();
//	}
	public String getName(String selectedRestaurant) throws SQLException {
	    PreparedStatement ps = connection.prepareStatement("SELECT ID FROM Restaurants WHERE RestaurantName = ? LIMIT 1");
	    ps.setString(1, selectedRestaurant);
	    ResultSet rs = ps.executeQuery();
	    StringBuilder reviews = new StringBuilder();
	    while (rs.next()) {
	        // Retrieve each column value separately
	        int id = rs.getInt("ID");
	        
	        
	        // Append the retrieved values to the StringBuilder
	        reviews.append(id);
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

