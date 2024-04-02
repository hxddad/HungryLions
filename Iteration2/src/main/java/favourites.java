

import java.sql.*;

public class favourites {
	Connection connection;
	Statement statement;
	
	
	public favourites() {
		try {
			connection = DatabaseConnection.connect("users");
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
	}
	
	// Returns true if it is added, false if it already exists therefore not added.
	public boolean addFavorites(String restaurant, String username) throws SQLException {
		PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM FAVORITES WHERE username = ? AND favorite_restaurant = ?");
		ps1.setString(1, username);
		ps1.setString(2, restaurant);
		ResultSet result = ps1.executeQuery();
		if (result.next()) {
			return false;
		}
		PreparedStatement ps = connection.prepareStatement("INSERT INTO FAVORITES (username, favorite_restaurant) VALUES (?, ?)");
		ps.setString(1, username);
		ps.setString(2, restaurant);
		ps.executeUpdate();
		return true;
	}
	
	public String getFavorites(String username) throws SQLException {
	    PreparedStatement ps = connection.prepareStatement("SELECT favorite_restaurant FROM FAVORITES WHERE username = ?");
	    ps.setString(1, username);
	    ResultSet rs = ps.executeQuery();
	    StringBuilder favorites = new StringBuilder();
	    while (rs.next()) {
	        favorites.append(rs.getString("favorite_restaurant")).append("\n");
	    }
	    rs.close();
	    ps.close();
	    return favorites.toString().trim();
	}

	
	public boolean removeFavorites(String restaurant, String username) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM FAVORITES WHERE username = ? AND favorite_restaurant = ?");
		ps.setString(1, username);
		ps.setString(2, restaurant);
		ResultSet result = ps.executeQuery();
		if (!result.isBeforeFirst() ) {    
			  return false;
			} 
		PreparedStatement ps1 = connection.prepareStatement("DELETE FROM FAVORITES WHERE username = ? AND favorite_restaurant = ?");
		ps1.setString(1, username);
		ps1.setString(2, restaurant);
		ps1.executeUpdate();
		return true;

		
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


