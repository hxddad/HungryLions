
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.sql.*;

public class userdb {
	
	static String jdbcURL = "jdbc:sqlite:/C:\\Users\\jason\\EECS2311-Project\\Iteration1\\HungryLions\\users.db";
	Connection connection;
	Statement statement;
	
	
	public userdb() throws SQLException {
		
		try {
			connection = DriverManager.getConnection(jdbcURL);
			statement = connection.createStatement();
			
			
		}
		catch (SQLException e){
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
		PreparedStatement ps1 = connection.prepareStatement("INSERT INTO USERS (username, password) VALUES (?, ?)");
		ps1.setString(1, username);
		ps1.setString(2, password);
		return true;
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
	
	
	public static void main(String args[]) throws SQLException {
		userdb test = new userdb();
		System.out.println(test.verifyPassword("sad", "asdasd"));
		
	}

	

}
