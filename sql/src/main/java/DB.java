import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
		public static void main(String[] args) throws SQLException {
			String jdbcUrl = "jdbc:sqlite:/C:\\Users\\jason\\EECS2311-Project\\Iteration1\\HungryLions\\users.db";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "SELECT * FROM users";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					String name = result.getString("username");
					String pw = result.getString("password");
					System.out.println(name + pw);
				}
				
				
			}
			catch (SQLException e) {
				System.out.println("Error connecting to SQLite database");
				e.printStackTrace();
			}
			
		}
}
