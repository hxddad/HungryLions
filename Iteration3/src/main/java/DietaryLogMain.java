
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DietaryLogMain {
    private List<String> foodItems;
    private Connection connection;
	private Statement statement;
    public DietaryLogMain() {
        foodItems = new ArrayList<>();
        try {
			connection = DatabaseConnection.connect("log");
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
    }
    public boolean logFoodItem(String foodItem) {
        if (foodItem == null || foodItem.isEmpty()) {
            System.out.println("Error: Please enter a valid food item. Spaces are not allowed as an entry.");
            return false;
        }

        if (countWords(foodItem) > 5) {
            System.out.println("Error: Please enter a food item with 5 words or less.");
            return false;
        }

        if (!containsLetters(foodItem)) {
            System.out.println("Error: Please enter a valid food item with alphabetic characters.");
            return false;
        }

        foodItems.add(foodItem);
        System.out.println("Food item '" + foodItem + "' logged successfully.");
        return true;
    }

    public List<String> getLoggedFoodItems() {
        return foodItems;
    }

    public static void main(String[] args) {
        // Creating a dietary log instance
        DietaryLogMain dietaryLog = new DietaryLogMain();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompting the user to log food items
        System.out.println("Welcome to the Dietary Log App!");
        System.out.println("Please enter the food items you ate today (enter 'done' to finish):");

        // Input loop
        while (true) {
            String input = scanner.nextLine().trim();

            // Exit loop if user enters 'done'
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            // Logging food item
         // Logging food item
            if (!dietaryLog.logFoodItem(input)) {
                // If logging failed due to validation, prompt the user to retry
                System.out.println("Please try again.");
            }
        }

        scanner.close();

        // Displaying logged food items
        System.out.println("Logged Food Items:");
        for (String foodItem : dietaryLog.getLoggedFoodItems()) {
            System.out.println(foodItem);
        }
    }

    // Helper method to count words in a string
    private static int countWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] words = input.split("\\s+");
        return words.length;
    }

    // Helper method to check if a string contains alphabetic characters
    private static boolean containsLetters(String input) {
        return input.matches(".*[a-zA-Z].*");
    }
}
