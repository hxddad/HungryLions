
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodSearchProgram {
    public static void main(String[] args) {
        // Sample food spots
    	final List<Restaurant> restaurants = new ArrayList<>();

         {
        	restaurants.add(new Restaurant(14, "Chop'd & Wrap'd", "Central Square (CSQ)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
        	restaurants.add(new Restaurant(15, "Chop'd & Wrap'd", "Dahdaleh Building (DB)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
        	restaurants.add(new Restaurant(16, "Chop'd & Wrap'd", "Winters College (WC)", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "high"));
        	restaurants.add(new Restaurant(17, "Chungchun Rice Hot Dog", "The Quad", "Korean", "Cash, Credit Card, Debit Card", "Halal (H), Vegan (V), Vegetarian (VG)", "low"));
        	restaurants.add(new Restaurant(18, "Country Style (Kosher Deli)", "Winters College (WC)", "Mediterranean", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Kosher (K), Vegan (V), Vegetarian (VG)", "high"));
        	restaurants.add(new Restaurant(19, "Create Stir Fry", "Stong College (SC)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegetarian (VG)", "medium"));
        	restaurants.add(new Restaurant(20, "Create Stir Fry", "Central Square (CSQ)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "medium"));
        	restaurants.add(new Restaurant(21, "Create Stir Fry", "Winters College (WC)", "Italian, Southeast Asian", "Cash, Credit Card, Debit Card, Meal Plan (MP), Meal Plan Plus (MP+)", "Halal (H), Vegetarian (VG)", "medium"));
        	restaurants.add(new Restaurant(22, "Crepe Delicious", "The Quad", "French", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), Vegan (V), Vegetarian (VG)", "medium"));
        	restaurants.add(new Restaurant(23, "Cucina Italian Cafe", "York Lanes Mall", "Italian", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Vegan (V), Vegetarian (VG)", "high"));
        	restaurants.add(new Restaurant(24, "Executive Dining Room", "Schulich Dining", "Global Cuisines", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), No Gluten (NG), Vegan (V), Vegetarian (VG)", "expensive"));
        	restaurants.add(new Restaurant(25, "Fat Bastard Burrito", "The Quad", "Mexican", "Cash, Credit Card, Debit Card", "No Gluten (NG), Vegan (V), Vegetarian (VG)", "low"));
        	restaurants.add(new Restaurant(26, "Galito's Flamed Grilled Chicken", "The Quad", "South African", "Cash, Credit Card, Debit Card, Meal Plan Plus (MP+)", "Halal (H), Vegan (V)", "medium"));

        }

        // Prompt user for filters
        Scanner scanner = new Scanner(System.in);

        String desiredCuisine;
        do {
            System.out.println("Enter the type of food you want (e.g., Italian, Southeast Asian, North American, Greek, Japanese, Mexican, Mediterranean, Korean, Global Cuisines):");
            desiredCuisine = scanner.nextLine();
        } while (!isValidCuisine(desiredCuisine));

        boolean isHalal = getYesNoInput("Do you want halal?");
        boolean isGlutenFree = getYesNoInput("Do you want gluten-free?");
        boolean isVegan = getYesNoInput("Do you want vegan?");
        boolean isVegetarian = getYesNoInput("Do you want vegetarian?");

        // Search and display results
        List<Restaurant> filteredFoodSpots = searchFoodSpots(restaurants, desiredCuisine, isHalal, isGlutenFree, isVegan, isVegetarian);

        System.out.println("\nSearch Results:");
        if (filteredFoodSpots.isEmpty()) {
            System.out.println("No matching food spots found.");
        } else {
            for (Restaurant foodSpot : filteredFoodSpots) {
                System.out.println("Name: " + foodSpot.getName() + ", Cuisine: " + foodSpot.getCuisine());
            }
        }
    }

    private static boolean isValidCuisine(String cuisine) {
        String[] validCuisines = {"Italian", "Southeast Asian", "North American", "Greek", "Japanese", "Mexican", "Mediterranean", "Korean", "Global Cuisines"};
        for (String validCuisine : validCuisines) {
            if (validCuisine.equalsIgnoreCase(cuisine)) {
                return true;
            }
        }
        System.out.println("Invalid cuisine type. Please enter a valid cuisine.");
        return false;
    }

    private static boolean getYesNoInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println(message + " (yes/no):");
            input = scanner.nextLine().toLowerCase();
        } while (!input.equals("yes") && !input.equals("no"));

        return input.equals("yes");
    }

    private static List<Restaurant> searchFoodSpots(List<Restaurant> restaurants, String desiredCuisine, boolean isHalal, boolean isGlutenFree, boolean isVegan, boolean isVegetarian) {
        List<Restaurant> results = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCuisine().equalsIgnoreCase(desiredCuisine)
                    && (!isHalal || restaurant.isHalal())
                    && (!isGlutenFree || restaurant.isGlutenFree())
                    && (!isVegan || restaurant.isVegan())
                    && (!isVegetarian || restaurant.isVegetarian())) {
                results.add(restaurant);
            }
        }

        return results;
    

    }
}