   
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
 
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
 
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
	
	// Returns false if user already has account, true if account has been successfully added.
	public boolean addUser(String username, String password) throws SQLException, NoSuchAlgorithmException {
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
	        ps1.setString(2, toHexString(getSHA(password)));
	        int rowsAffected = ps1.executeUpdate(); // Execute the insertion statement
	        return rowsAffected > 0; // Return true if insertion was successful
	    //}
	}
	
	// Returns true if authenticated, false otherwise.
	public boolean verifyPassword(String username, String password) throws SQLException, NoSuchAlgorithmException {
	    PreparedStatement ps = null;
	    ResultSet resultSet = null;
	        ps = connection.prepareStatement("SELECT * FROM USERS WHERE username = ?");
	        ps.setString(1, username);
	        resultSet = ps.executeQuery();
	        
	        while (resultSet.next()) {
	            if (Objects.equals(resultSet.getString("password"), toHexString(getSHA(password)))) {
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