import java.sql.*;
import java.util.Objects;

public class user_db {

    private Connection connection;

    public user_db() throws SQLException {
        try  {
            connection = DriverManager.getConnection("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:init.sql';");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addUser(String username, String password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO USERS (username, password) VALUES (?, ?)");
        ps.setString(1, username);
        ps.setString(2, password);

    }



    public boolean verifyPassword(String username, String password) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("select * FROM USERS WHERE username = ?");
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            if (!Objects.equals(resultSet.getString("password"), password)) return false;
        }
        return true;
    }



    public static void main(String args[]) {

    }
}
