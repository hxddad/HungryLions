package tests;

public class DietaryLogTest {
    public static void main(String[] args) {
        // Test Case 1: Logging food items
        testLogging();

        // Test Case 2: Retrieving logged food items
        testRetrieving();

        // Test Case 3: Logging multiple food items
        testMultipleLogging();

        // Test Case 4: Retrieving empty log
        testEmptyLog();

        // Test Case 5: Logging empty food item
        testEmptyLogging();

        // Test Case 6: Logging a food item with more than 5 words
        testExceedingWordLimit();

        // Test Case 7: Logging a food item with only numbers or special characters
        testInvalidInput();
    }

    // Test Case 1: Logging food items
    public static void testLogging() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        assert dietaryLog.logFoodItem("Chicken Salad");
        assert dietaryLog.logFoodItem("Banana");
        assert dietaryLog.logFoodItem("Brown Rice");

        assert dietaryLog.getLoggedFoodItems().size() == 3;
    }

    // Test Case 2: Retrieving logged food items
    public static void testRetrieving() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        dietaryLog.logFoodItem("Chicken Salad");
        dietaryLog.logFoodItem("Banana");
        dietaryLog.logFoodItem("Brown Rice");

        assert dietaryLog.getLoggedFoodItems().size() == 3;
    }

    // Test Case 3: Logging multiple food items
    public static void testMultipleLogging() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        simulateUserInput(dietaryLog, "Chicken Salad");
        simulateUserInput(dietaryLog, "Banana");
        simulateUserInput(dietaryLog, "Brown Rice");
        simulateUserInput(dietaryLog, "Apple");

        assert dietaryLog.getLoggedFoodItems().size() == 4;
    }

    // Test Case 4: Retrieving empty log
    public static void testEmptyLog() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        assert dietaryLog.getLoggedFoodItems().isEmpty();
    }

    // Test Case 5: Logging empty food item
    public static void testEmptyLogging() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        simulateUserInput(dietaryLog, "");
        assert !dietaryLog.logFoodItem("");
        assert dietaryLog.getLoggedFoodItems().isEmpty();
    }

    // Test Case 6: Logging a food item with more than 5 words
    public static void testExceedingWordLimit() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        simulateUserInput(dietaryLog, "This is a food item with more than 5 words");
        assert !dietaryLog.logFoodItem("This is a food item with more than 5 words");
        assert dietaryLog.getLoggedFoodItems().isEmpty();
    }

    // Test Case 7: Logging a food item with only numbers or special characters
    public static void testInvalidInput() {
        DietaryLogMain dietaryLog = new DietaryLogMain();
        simulateUserInput(dietaryLog, "12345");
        assert !dietaryLog.logFoodItem("12345");
        assert dietaryLog.getLoggedFoodItems().isEmpty();
    }

    // Helper method to simulate user input
    public static void simulateUserInput(DietaryLogMain dietaryLog, String input) {
        if (!input.isEmpty() && countWords(input) <= 5 && containsLetters(input)) {
            dietaryLog.logFoodItem(input);
        } else {
            System.out.println("False");
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