package DietaryFilter;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DietaryFilter {
	
	public static void main(String[] args) throws FileNotFoundException {
		listAll("restaurants.txt");
		System.out.println("\n");
		listHalal("restaurants.txt");
		System.out.println("\n");
		listKosher("restaurants.txt");
		System.out.println("\n");
		listNoGluten("restaurants.txt");
		System.out.println("\n");
		listVegan("restaurants.txt");
		System.out.println("\n");
		listVegetarian("restaurants.txt");
		
	}
	
	public static String listAll(String file) throws FileNotFoundException{
		File restaurants = new File(file);
		 StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	                if (col.length >= 2) { 
	                    result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
	        }
	}
	
    
	public static String listHalal(String file) throws FileNotFoundException {
		File restaurants = new File(file);
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Halal (H)") && col.length >= 2) {
	                	result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
		}
	}
	
	public static String listKosher(String file) throws FileNotFoundException {
		File restaurants = new File(file);
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Kosher (K)") && col.length >= 2) {
	                	result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
		}
	}
	
	public static String listVegan(String file) throws FileNotFoundException {
		File restaurants = new File(file);
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Vegan (V)") && col.length >= 2) {
	                	result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
		}
	}
			
	public static String listVegetarian(String file) throws FileNotFoundException {
		File restaurants = new File(file);
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("Vegetarian (VG)") && col.length >= 2) {
	                	result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
		}
	}
	
	public static String listNoGluten(String file) throws FileNotFoundException {
		File restaurants = new File(file);
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	              
	                if (line.contains("No Gluten (NG)") && col.length >= 2) {
	                	result.append(col[0]).append(" [").append(col[1]).append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;
		}
	}
}
