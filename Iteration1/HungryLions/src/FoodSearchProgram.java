package SearchFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodSearchProgram {
    public static void main(String[] args) {
        // Sample food spots
        List<FoodSpot> foodSpots = new ArrayList<>();
        foodSpots.add(new FoodSpot("Marcello's", "Italian", false, false, false, true));
        foodSpots.add(new FoodSpot("Thai Express", "Southeast Asian", true, true, false, true));
        foodSpots.add(new FoodSpot("Burger Priest", "North American", false, false, false, false));
        foodSpots.add(new FoodSpot("Mr. Greek", "Greek", true, true, true, true));
        foodSpots.add(new FoodSpot("Sushi Haven", "Japanese", false, true, false, true));
        foodSpots.add(new FoodSpot("Taco Fiesta", "Mexican", true, false, false, true));
        foodSpots.add(new FoodSpot("Mediterranean Delight", "Mediterranean", true, true, true, false));
        foodSpots.add(new FoodSpot("Woojoo Bunsik", "Korean", false, true, true, false));
        foodSpots.add(new FoodSpot("Sofra", "Global Cuisines", false, true, false, false));

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
        List<FoodSpot> filteredFoodSpots = searchFoodSpots(foodSpots, desiredCuisine, isHalal, isGlutenFree, isVegan, isVegetarian);

        System.out.println("\nSearch Results:");
        if (filteredFoodSpots.isEmpty()) {
            System.out.println("No matching food spots found.");
        } else {
            for (FoodSpot foodSpot : filteredFoodSpots) {
                System.out.println("Name: " + foodSpot.name + ", Cuisine: " + foodSpot.cuisineType);
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

    private static List<FoodSpot> searchFoodSpots(List<FoodSpot> foodSpots, String desiredCuisine, boolean isHalal, boolean isGlutenFree, boolean isVegan, boolean isVegetarian) {
        List<FoodSpot> results = new ArrayList<>();

        for (FoodSpot foodSpot : foodSpots) {
            if (foodSpot.cuisineType.equalsIgnoreCase(desiredCuisine)
                    && (!isHalal || foodSpot.isHalal)
                    && (!isGlutenFree || foodSpot.isGlutenFree)
                    && (!isVegan || foodSpot.isVegan)
                    && (!isVegetarian || foodSpot.isVegetarian)) {
                results.add(foodSpot);
            }
        }

        return results;
    }
}