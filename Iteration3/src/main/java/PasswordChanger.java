import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordChanger {
	
	

    // Method to change the password
    public static boolean changePassword(String username, String newPassword) throws NoSuchAlgorithmException {
        if (newPassword.length() < 5) {
            System.out.println("New password must be at least 5 characters long.");
            return false;
        }

        try (Connection conn = DatabaseConnection.connect("users")) {
            // Check if the new password is different from the old one
            String checkSql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, username);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && newPassword.equals(rs.getString("password"))) {
                    System.out.println("New password must be different from the current password.");
                    return false;
                }
            }

            // Update the password
            String updateSql = "UPDATE users SET password = ? WHERE username = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, userdb.toHexString(userdb.getSHA(newPassword)));
                updateStmt.setString(2, username);
                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Password changed successfully.");
                    return true;
                } else {
                    System.out.println("User not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
