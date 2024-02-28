import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String APP_DB_URL = "jdbc:sqlite:Appdb.db";
    private static final String REVIEW_DB_URL = "jdbc:sqlite:Review.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    public static Connection connect(String dbName) {
        String url = dbName.equalsIgnoreCase("app") ? APP_DB_URL : REVIEW_DB_URL;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database " + dbName + " has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
