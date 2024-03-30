package DietaryFilter;

import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DietaryFilter {
		
	public static String listRestaraunts(String file) throws 
	FileNotFoundException, NotADietaryRestriction{
		File restaurants = new File(file);
		 StringBuilder result = new StringBuilder();
		 try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   ");
	                if (col.length >= 2) { 
	                    result.append(col[0]).append(" [").append(col[1])
	                    .append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;	        
	   }
	}
	
	public static String listRestaraunts(String file, String filter) throws 
	FileNotFoundException, NotADietaryRestriction{
		
		 List<String> validFilters = Arrays.asList
				 ("Halal (H)", "Kosher (K)", "Vegan (V)", "Vegetarian (VG)", "No Gluten (NG)");
	      
	        if (!validFilters.contains(filter)) {
	            throw new NotADietaryRestriction("Not a dietary restriction.");
	        }
	
	        File restaurants = new File(file);
	        StringBuilder result = new StringBuilder();
	        try (Scanner scanner = new Scanner(restaurants)) {
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] col = line.split("   "); 
	                if (line.contains(filter) && col.length >= 2) { 
	                    result.append(col[0]).append(" [").append(col[1])
	                    .append("]").append("\n");
	                }
	            }
	            return result.toString(); 
	        } catch (FileNotFoundException e) {
	            throw e;	        
	        }
	    }
}
