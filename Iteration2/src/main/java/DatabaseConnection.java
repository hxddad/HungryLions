import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String APP_DB_URL = "jdbc:sqlite:./db/Appdb.db";
    private static final String REVIEW_DB_URL = "jdbc:sqlite:./db/Review.db";
    private static final String USER_DB_URL = "jdbc:sqlite:./db/users.db";
    private static final String LOG_DB_URL = "jdbc:sqlite:./db/logdb.db";


    public static Connection connect(String dbName) {
        String url;
        switch (dbName.toLowerCase()) {
            case "app":
                url = APP_DB_URL;
                break;
            case "review":
                url = REVIEW_DB_URL;
                break;
            case "users":
                url = USER_DB_URL; 
                break;
            case "log":
                url = LOG_DB_URL; 
                break;
            default:
                throw new IllegalArgumentException("Invalid database name: " + dbName);
        }
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database " + dbName + " has been established.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}

