
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.sql.*;

public class userdb {
    Connection connection;
    Statement statement;

    public userdb() {
        try {
            connection = DatabaseConnection.connect("users");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }
    }
	
	
	// Returns false if user already has account, true if account has been successfully added.
	public boolean addUser(String username, String password) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT USERNAME FROM USERS WHERE USERNAME = ?");
		ps.setString(1, username);
		ResultSet result = ps.executeQuery();
		
		if (result.isBeforeFirst() ) {    
		  return false;
		} 
		//else {
	        // Username doesn't exist, proceed with insertion
	        PreparedStatement ps1 = connection.prepareStatement("INSERT INTO USERS (username, password) VALUES (?, ?)");
	        ps1.setString(1, username);
	        ps1.setString(2, password);
	        int rowsAffected = ps1.executeUpdate(); // Execute the insertion statement
	        return rowsAffected > 0; // Return true if insertion was successful
	    //}
	}
	
	// Returns true if authenticated, false otherwise.
	public boolean verifyPassword(String username, String password) throws SQLException {
	    PreparedStatement ps = null;
	    ResultSet resultSet = null;
	        ps = connection.prepareStatement("SELECT * FROM USERS WHERE username = ?");
	        ps.setString(1, username);
	        resultSet = ps.executeQuery();
	        
	        while (resultSet.next()) {
	            if (Objects.equals(resultSet.getString("password"), password)) {
	                return true;
	            }
	        }
	    return false;
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