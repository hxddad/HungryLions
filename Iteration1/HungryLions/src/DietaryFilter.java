package DietaryFilter;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DietaryFilter {
	
	public static void main(String[] args) {
		listHalal();
		System.out.println("\n");
		listKosher();
		System.out.println("\n");
		listNoGluten();
		System.out.println("\n");
		listVegan();
		System.out.println("\n");
		listVegetarian();
	}
    
	private static void listHalal() {
		File file = new File("restaurants.txt");
		 try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Halal (H)")) {
	                	System.out.println(col[0] + " [" + col[1] + "]");
	                }
	            }
		 } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
		}
	}
	
	private static void listKosher() {
		File file = new File("restaurants.txt");
		 try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Kosher (K)")) {
	                	System.out.println(col[0] + " [" + col[1] + "]");
	                }
	            }
		 } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
		}
	}
	
	private static void listVegan() {
		File file = new File("restaurants.txt");
		 try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Vegan (V)")) {
	                	System.out.println(col[0] + " [" + col[1] + "]");
	                }
	            }
		 } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
		}
	}
	
	private static void listVegetarian() {
		File file = new File("restaurants.txt");
		 try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Vegetarian (VG)")) {
	                	System.out.println(col[0] + " [" + col[1] + "]");
	                }
	            }
		 } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
		}
	}
	
	private static void listNoGluten() {
		File file = new File("restaurants.txt");
		 try (Scanner scanner = new Scanner(file)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("No Gluten (NG)")) {
	                	System.out.println(col[0] + " [" + col[1] + "]");
	                }
	            }
		 } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
		}
	}
}

