package DietaryFilter;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DietaryFilter {
	
	public static void main(String[] args) throws FileNotFoundException {
		ListAll();
		System.out.println("\n");
		ListHalal();
		System.out.println("\n");
		ListKosher();
		System.out.println("\n");
		ListNoGluten();
		System.out.println("\n");
		ListVegan();
		System.out.println("\n");
		ListVegetarian();
		
	}
	
	public static String ListAll() throws FileNotFoundException{
		File file = new File("restaurants.txt");
		 StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
	
    
	public static String ListHalal() throws FileNotFoundException {
		File file = new File("restaurants.txt");
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
	
	public static String ListKosher() throws FileNotFoundException {
		File file = new File("restaurants.txt");
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
	
	public static String ListVegan() throws FileNotFoundException {
		File file = new File("restaurants.txt");
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
			
	public static String ListVegetarian() throws FileNotFoundException {
		File file = new File("restaurants.txt");
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
	
	public static String ListNoGluten() throws FileNotFoundException {
		File file = new File("restaurants.txt");
		StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(file)) {
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
