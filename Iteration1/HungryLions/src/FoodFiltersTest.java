package DietaryFilter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.manipulation.Filter;

class FoodFiltersTest {
	
	// helper method for catching exception case
	
    String ListAllException() throws FileNotFoundException { 
        File file = new File("grades.txt"); // different file names
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] col = line.split("   ");
                if (col.length >= 2) { 
                    result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
                }
            }
        }
        return result.toString(); 
    }
    
	@Test
	void TestFileNotFoundExcpetion() {
		assertThrows(FileNotFoundException.class, (() -> ListAllException()));
	}
	
	@Test
	void TestNotThrowException() {
		assertDoesNotThrow(() -> DietaryFilter.ListAll(), "Does not throw Exception.");
	}
	
	@Test
	void TestListAll() throws FileNotFoundException {
		String[] restaurants = {
			    "416 Burger Co. [Dahdaleh Building (DB)]",
			    "416 Burger Co. [Central Square (CSQ)]",
			    "Amaya Express [The Quad]",
			    "Aroma Espresso Bar [York Lanes Mall]",
			    "Aroma Espresso Bar [The Quad]",
			    "Basil Box [The Quad]",
			    "Booster Juice [The Quad]",
			    "Booster Juice [Central Square (CSQ)]",
			    "Burger King [The Quad]",
			    "Chaska Indian Street Food [York Lanes Mall]",
			    "Chef's Table [Winters College (WC)]",
			    "Chef's Table [Stong College (SC)]",
			    "Chipotle Mexican Grill [The Quad]",
			    "Chop'd & Wrap'd [Central Square (CSQ)]",
			    "Chop'd & Wrap'd [Dahdaleh Building (DB)]",
			    "Chop'd & Wrap'd [Winters College (WC)]",
			    "Chungchun Rice Hot Dog [The Quad]",
			    "Country Style (Kosher Deli) [Winters College (WC)]",
			    "Create Stir Fry [Stong College (SC)]",
			    "Create Stir Fry [Central Square (CSQ)]",
			    "Create Stir Fry [Winters College (WC)]",
			    "Crepe Delicious [The Quad]",
			    "Cucina Italian Cafe [York Lanes Mall]",
			    "Executive Dining Room [Schulich Dining]",
			    "Fat Bastard Burrito [The Quad]",
			    "Galito's Flamed Grilled Chicken [The Quad]",
			    "Gateway [First Student Centre]",
			    "Gino's Pizza [First Student Centre]",
			    "Gong cha [The Quad]",
			    "Grill House [Stong College (SC)]",
			    "Grill House [Winters College (WC)]",
			    "Hero Certified Burgers [York Lanes Mall]",
			    "Insomnia Cookies [The Quad]",
			    "Jimmy The Greek [First Student Centre]",
			    "KitchenMate [Glendon Campus]",
			    "KitchenMate [School of Continuing Studies (SCS)]",
			    "La Prep [York Lanes Mall]",
			    "Mac's Sushi [First Student Centre]",
			    "Mandarin 2 Go [The Quad]",
			    "Marché - BokChoy [Glendon Campus]",
			    "Marché - Chef's Table [Glendon Campus]",
			    "Marché - Pizza Pizza [Glendon Campus]",
			    "Marché - Pop Up Kitchen [Glendon Campus]",
			    "Marché - Stacks [Glendon Campus]",
			    "Market (Certified Gluten Free Facility) [Bergeron Centre (BRG)]",
			    "Meltwich Food Co. [The Quad]",
			    "Omni Noodle [The Quad]",
			    "Osmow's Shawarma [The Quad]",
			    "Pagoda Tree [First Student Centre]",
			    "Palgong Tea [Lassonde Building (LAS)]",
			    "Pita Land [York Lanes Mall]",
			    "Pizza Pizza [Stong College (SC)]",
			    "Pizza Pizza [Winters College (WC)]",
			    "Pizza Pizza [Central Square (CSQ)]",
			    "Pizza Studio [The Quad]",
			    "Pop Up Pasta [Central Square (CSQ)]",
			    "Popeyes [York Lanes Mall]",
			    "Qoola FRESH [York Lanes Mall]",
			    "Revolution Noodles [Dahdaleh Building (DB)]",
			    "Sakura [York Lanes Mall]",
			    "Salus Fresh Food [The Quad]",
			    "Schulich Market Café [Schulich Dining]",
			    "Smoke's Poutinerie [Dahdaleh Building (DB)]",
			    "Starbucks [Central Square (CSQ)]",
			    "Subway [Central Square (CSQ)]",
			    "Subway [The Quad]",
			    "Sushi Shop [York Lanes Mall]",
			    "Sushi-Q [The Quad]",
			    "Thai Express [York Lanes Mall]",
			    "The Break Café [Second Student Centre]",
			    "The Break Room [First Student Centre]",
			    "The Campus Bubble Tea [York Lanes Mall]",
			    "The Great Canadian Bagel [York Lanes Mall]",
			    "The Islands Caribbean Cookshop [First Student Centre]",
			    "The Orange Snail [Stong College (SC)]",
			    "Tim Hortons [Dahdaleh Building (DB)]",
			    "Tim Hortons [Glendon Campus]",
			    "Tim Hortons [William Small Centre (WSC)]",
			    "Timbers Lodge [York Lanes Mall]",
			    "Treats [First Student Centre]",
			    "Tuchner's Pub [Schulich Dining]",
			    "Wendy's [First Student Centre, William Small Centre (WSC)]",
			    "World Kitchen [Central Square (CSQ)]",
			    "Yogen Fruz [First Student Centre]",
			    "z-teca Mexican Eatery [York Lanes Mall]"
			};
		
		StringBuilder builder = new StringBuilder();
        for (String item : restaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListAll();
        assertEquals(expected, actual, "Does not list restaurants properly.");
	}
	
	@Test
	void TestListHalal() throws FileNotFoundException {
		String[] halalRestaurants = {
			    "416 Burger Co. [Dahdaleh Building (DB)]",
			    "416 Burger Co. [Central Square (CSQ)]",
			    "Amaya Express [The Quad]",
			    "Basil Box [The Quad]",
			    "Chaska Indian Street Food [York Lanes Mall]",
			    "Chef's Table [Winters College (WC)]",
			    "Chef's Table [Stong College (SC)]",
			    "Chop'd & Wrap'd [Central Square (CSQ)]",
			    "Chop'd & Wrap'd [Dahdaleh Building (DB)]",
			    "Chop'd & Wrap'd [Winters College (WC)]",
			    "Chungchun Rice Hot Dog [The Quad]",
			    "Country Style (Kosher Deli) [Winters College (WC)]",
			    "Create Stir Fry [Stong College (SC)]",
			    "Create Stir Fry [Central Square (CSQ)]",
			    "Create Stir Fry [Winters College (WC)]",
			    "Crepe Delicious [The Quad]",
			    "Executive Dining Room [Schulich Dining]",
			    "Galito's Flamed Grilled Chicken [The Quad]",
			    "Grill House [Stong College (SC)]",
			    "Grill House [Winters College (WC)]",
			    "Hero Certified Burgers [York Lanes Mall]",
			    "KitchenMate [Glendon Campus]",
			    "KitchenMate [School of Continuing Studies (SCS)]",
			    "Marché - BokChoy [Glendon Campus]",
			    "Marché - Chef's Table [Glendon Campus]",
			    "Marché - Pizza Pizza [Glendon Campus]",
			    "Marché - Pop Up Kitchen [Glendon Campus]",
			    "Marché - Stacks [Glendon Campus]",
			    "Market (Certified Gluten Free Facility) [Bergeron Centre (BRG)]",
			    "Meltwich Food Co. [The Quad]",
			    "Osmow's Shawarma [The Quad]",
			    "Pagoda Tree [First Student Centre]",
			    "Pita Land [York Lanes Mall]",
			    "Pizza Pizza [Stong College (SC)]",
			    "Pizza Pizza [Winters College (WC)]",
			    "Pop Up Pasta [Central Square (CSQ)]",
			    "Popeyes [York Lanes Mall]",
			    "Qoola FRESH [York Lanes Mall]",
			    "Revolution Noodles [Dahdaleh Building (DB)]",
			    "Sakura [York Lanes Mall]",
			    "Schulich Market Café [Schulich Dining]",
			    "Smoke's Poutinerie [Dahdaleh Building (DB)]",
			    "Sushi Shop [York Lanes Mall]",
			    "Sushi-Q [The Quad]",
			    "The Break Room [First Student Centre]",
			    "The Islands Caribbean Cookshop [First Student Centre]",
			    "The Orange Snail [Stong College (SC)]",
			    "Timbers Lodge [York Lanes Mall]",
			    "World Kitchen [Central Square (CSQ)]",
			    "z-teca Mexican Eatery [York Lanes Mall]"
			};
		StringBuilder builder = new StringBuilder();
        for (String item : halalRestaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListHalal();
        assertEquals(expected, actual, "Does not filter restaurants properly.");
	}
	
	@Test
	void TestListKosher() throws FileNotFoundException {
		String[] kosherRestaurants = {
			    "Country Style (Kosher Deli) [Winters College (WC)]",
			    "KitchenMate [Glendon Campus]",
			    "KitchenMate [School of Continuing Studies (SCS)]"
			};
		StringBuilder builder = new StringBuilder();
        for (String item : kosherRestaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListKosher();
        assertEquals(expected, actual, "Does not filter restaurants properly.");
	}
	
	@Test
	void TestListVegan() throws FileNotFoundException{
		String[] veganRestaurants = {
				 "416 Burger Co. [Dahdaleh Building (DB)]",
				    "416 Burger Co. [Central Square (CSQ)]",
				    "Amaya Express [The Quad]",
				    "Aroma Espresso Bar [York Lanes Mall]",
				    "Aroma Espresso Bar [The Quad]",
				    "Basil Box [The Quad]",
				    "Booster Juice [The Quad]",
				    "Booster Juice [Central Square (CSQ)]",
				    "Burger King [The Quad]",
				    "Chaska Indian Street Food [York Lanes Mall]",
				    "Chef's Table [Winters College (WC)]",
				    "Chef's Table [Stong College (SC)]",
				    "Chop'd & Wrap'd [Central Square (CSQ)]",
				    "Chop'd & Wrap'd [Dahdaleh Building (DB)]",
				    "Chop'd & Wrap'd [Winters College (WC)]",
				    "Chungchun Rice Hot Dog [The Quad]",
				    "Country Style (Kosher Deli) [Winters College (WC)]",
				    "Create Stir Fry [Central Square (CSQ)]",
				    "Crepe Delicious [The Quad]",
				    "Cucina Italian Cafe [York Lanes Mall]",
				    "Executive Dining Room [Schulich Dining]",
				    "Fat Bastard Burrito [The Quad]",
				    "Galito's Flamed Grilled Chicken [The Quad]",
				    "Gong cha [The Quad]",
				    "Grill House [Stong College (SC)]",
				    "Grill House [Winters College (WC)]",
				    "Hero Certified Burgers [York Lanes Mall]",
				    "Jimmy The Greek [First Student Centre]",
				    "KitchenMate [Glendon Campus]",
				    "KitchenMate [School of Continuing Studies (SCS)]",
				    "La Prep [York Lanes Mall]",
				    "Mac's Sushi [First Student Centre]",
				    "Mandarin 2 Go [The Quad]",
				    "Marché - BokChoy [Glendon Campus]",
				    "Marché - Chef's Table [Glendon Campus]",
				    "Marché - Pop Up Kitchen [Glendon Campus]",
				    "Marché - Stacks [Glendon Campus]",
				    "Market (Certified Gluten Free Facility) [Bergeron Centre (BRG)]",
				    "Meltwich Food Co. [The Quad]",
				    "Osmow's Shawarma [The Quad]",
				    "Pagoda Tree [First Student Centre]",
				    "Palgong Tea [Lassonde Building (LAS)]",
				    "Pita Land [York Lanes Mall]",
				    "Pizza Studio [The Quad]",
				    "Qoola FRESH [York Lanes Mall]",
				    "Revolution Noodles [Dahdaleh Building (DB)]",
				    "Sakura [York Lanes Mall]",
				    "Salus Fresh Food [The Quad]",
				    "Smoke's Poutinerie [Dahdaleh Building (DB)]",
				    "Starbucks [Central Square (CSQ)]",
				    "Subway [Central Square (CSQ)]",
				    "Subway [The Quad]",
				    "Sushi Shop [York Lanes Mall]",
				    "Thai Express [York Lanes Mall]",
				    "The Break Room [First Student Centre]",
				    "The Great Canadian Bagel [York Lanes Mall]",
				    "The Islands Caribbean Cookshop [First Student Centre]",
				    "The Orange Snail [Stong College (SC)]",
				    "Tim Hortons [Dahdaleh Building (DB)]",
				    "Tim Hortons [William Small Centre (WSC)]",
				    "Timbers Lodge [York Lanes Mall]",
				    "World Kitchen [Central Square (CSQ)]",
				    "Yogen Fruz [First Student Centre]",
				    "z-teca Mexican Eatery [York Lanes Mall]"
				};
		StringBuilder builder = new StringBuilder();
        for (String item : veganRestaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListVegan();
        assertEquals(expected, actual, "Does not filter restaurants properly.");
	}
	
	@Test
	void TestListVegetarian() throws FileNotFoundException {
		String[] vegetarianRestaurants = {
			    "416 Burger Co. [Dahdaleh Building (DB)]",
			    "416 Burger Co. [Central Square (CSQ)]",
			    "Amaya Express [The Quad]",
			    "Aroma Espresso Bar [York Lanes Mall]",
			    "Aroma Espresso Bar [The Quad]",
			    "Basil Box [The Quad]",
			    "Booster Juice [The Quad]",
			    "Booster Juice [Central Square (CSQ)]",
			    "Burger King [The Quad]",
			    "Chaska Indian Street Food [York Lanes Mall]",
			    "Chef's Table [Winters College (WC)]",
			    "Chef's Table [Stong College (SC)]",
			    "Chop'd & Wrap'd [Central Square (CSQ)]",
			    "Chop'd & Wrap'd [Dahdaleh Building (DB)]",
			    "Chop'd & Wrap'd [Winters College (WC)]",
			    "Chungchun Rice Hot Dog [The Quad]",
			    "Country Style (Kosher Deli) [Winters College (WC)]",
			    "Create Stir Fry [Stong College (SC)]",
			    "Create Stir Fry [Central Square (CSQ)]",
			    "Create Stir Fry [Winters College (WC)]",
			    "Crepe Delicious [The Quad]",
			    "Cucina Italian Cafe [York Lanes Mall]",
			    "Executive Dining Room [Schulich Dining]",
			    "Fat Bastard Burrito [The Quad]",
			    "Grill House [Stong College (SC)]",
			    "Grill House [Winters College (WC)]",
			    "Hero Certified Burgers [York Lanes Mall]",
			    "Jimmy The Greek [First Student Centre]",
			    "KitchenMate [Glendon Campus]",
			    "KitchenMate [School of Continuing Studies (SCS)]",
			    "La Prep [York Lanes Mall]",
			    "Mac's Sushi [First Student Centre]",
			    "Marché - BokChoy [Glendon Campus]",
			    "Marché - Chef's Table [Glendon Campus]",
			    "Marché - Pizza Pizza [Glendon Campus]",
			    "Marché - Pop Up Kitchen [Glendon Campus]",
			    "Marché - Stacks [Glendon Campus]",
			    "Market (Certified Gluten Free Facility) [Bergeron Centre (BRG)]",
			    "Meltwich Food Co. [The Quad]",
			    "Osmow's Shawarma [The Quad]",
			    "Pagoda Tree [First Student Centre]",
			    "Palgong Tea [Lassonde Building (LAS)]",
			    "Pita Land [York Lanes Mall]",
			    "Pizza Pizza [Stong College (SC)]",
			    "Pizza Pizza [Winters College (WC)]",
			    "Pizza Pizza [Central Square (CSQ)]",
			    "Pizza Studio [The Quad]",
			    "Pop Up Pasta [Central Square (CSQ)]",
			    "Qoola FRESH [York Lanes Mall]",
			    "Revolution Noodles [Dahdaleh Building (DB)]",
			    "Sakura [York Lanes Mall]",
			    "Salus Fresh Food [The Quad]",
			    "Schulich Market Café [Schulich Dining]",
			    "Smoke's Poutinerie [Dahdaleh Building (DB)]",
			    "Starbucks [Central Square (CSQ)]",
			    "Subway [Central Square (CSQ)]",
			    "Subway [The Quad]",
			    "Sushi Shop [York Lanes Mall]",
			    "Sushi-Q [The Quad]",
			    "Thai Express [York Lanes Mall]",
			    "The Break Room [First Student Centre]",
			    "The Great Canadian Bagel [York Lanes Mall]",
			    "The Islands Caribbean Cookshop [First Student Centre]",
			    "The Orange Snail [Stong College (SC)]",
			    "Tim Hortons [Dahdaleh Building (DB)]",
			    "Tim Hortons [Glendon Campus]",
			    "Tim Hortons [William Small Centre (WSC)]",
			    "Timbers Lodge [York Lanes Mall]",
			    "World Kitchen [Central Square (CSQ)]",
			    "Yogen Fruz [First Student Centre]",
			    "z-teca Mexican Eatery [York Lanes Mall]"
			};
		StringBuilder builder = new StringBuilder();
        for (String item : vegetarianRestaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListVegetarian();
        assertEquals(expected, actual, "Does not filter restaurants properly.");
	}
	
	
	@Test
	void TestListNoGluten() throws FileNotFoundException {
		String[] noGlutenRestaurants = {
			    "416 Burger Co. [Dahdaleh Building (DB)]",
			    "416 Burger Co. [Central Square (CSQ)]",
			    "Aroma Espresso Bar [York Lanes Mall]",
			    "Aroma Espresso Bar [The Quad]",
			    "Basil Box [The Quad]",
			    "Chaska Indian Street Food [York Lanes Mall]",
			    "Executive Dining Room [Schulich Dining]",
			    "Fat Bastard Burrito [The Quad]",
			    "Grill House [Stong College (SC)]",
			    "Grill House [Winters College (WC)]",
			    "Hero Certified Burgers [York Lanes Mall]",
			    "Jimmy The Greek [First Student Centre]",
			    "KitchenMate [Glendon Campus]",
			    "KitchenMate [School of Continuing Studies (SCS)]",
			    "Mac's Sushi [First Student Centre]",
			    "Marché - BokChoy [Glendon Campus]",
			    "Market (Certified Gluten Free Facility) [Bergeron Centre (BRG)]",
			    "Meltwich Food Co. [The Quad]",
			    "Osmow's Shawarma [The Quad]",
			    "Pagoda Tree [First Student Centre]",
			    "Palgong Tea [Lassonde Building (LAS)]",
			    "Pita Land [York Lanes Mall]",
			    "Pizza Studio [The Quad]",
			    "Pop Up Pasta [Central Square (CSQ)]",
			    "Qoola FRESH [York Lanes Mall]",
			    "Salus Fresh Food [The Quad]",
			    "Smoke's Poutinerie [Dahdaleh Building (DB)]",
			    "Subway [Central Square (CSQ)]",
			    "Subway [The Quad]",
			    "Sushi Shop [York Lanes Mall]",
			    "Thai Express [York Lanes Mall]",
			    "The Great Canadian Bagel [York Lanes Mall]",
			    "The Islands Caribbean Cookshop [First Student Centre]",
			    "The Orange Snail [Stong College (SC)]",
			    "Timbers Lodge [York Lanes Mall]"
			};

		StringBuilder builder = new StringBuilder();
        for (String item : noGlutenRestaurants) {
            builder.append(item).append("\n");
        }
        String expected = builder.toString();
        var actual = DietaryFilter.ListNoGluten();
        assertEquals(expected, actual, "Does not filter restaurants properly.");
	}
}
