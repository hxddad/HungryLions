package Main;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final String JSON_FILE_PATH = "users.json";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	while (true) {
            System.out.println("Welcome to Our App!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
            	String username=scanner.nextLine();
            	String password=scanner.nextLine();
            	String[] restrictions={"Kosher","Vegan"};
            	String campusAffiliation=scanner.nextLine();
            	boolean mealPlan=true;
            	signUp( username,  password, restrictions,  campusAffiliation,  mealPlan);
            } 
            else if (option == 2) {
            	String username=scanner.nextLine();
            	String password=scanner.nextLine();
            	User user = login(username, password);
            	System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getDietaryRestrictions()+" "+user.getCampusAffiliation());
            } 
            else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }
    public static boolean userExists(String username) {
    	
        List<User> users = JsonThings.readUsersFromFile();
    	for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
    	return false;
    }
    public static void signUp(String username, String password, String[] restrictions, String campusAffiliation, boolean mealPlan) {
    	if(userExists(username)==false) {
    		User newUser = new User(username, password);
            for (String restriction : restrictions) {
    		newUser.getDietaryRestrictions().add(restriction);
            }
    		newUser.setCampusAffiliation(campusAffiliation);
            newUser.setMealPlanParticipation(mealPlan);
            List<User> users = JsonThings.readUsersFromFile();
            if(userExists(username)==false) {
            	users.add(newUser);
            }
            JsonThings.writeUsersToFile(users);
            System.out.println("Account created successfully!");
        }
     
    }

    public static User login(String username, String password) {
    	List<User> users = JsonThings.readUsersFromFile();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
               
                return user;
            }
        }
        System.out.println("Incorrect username or password. Please try again.");
        return null ;        

    }
    
    public static User getUserByUsername(String username) {
        List<User> users = JsonThings.readUsersFromFile();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
     // User not found
        return null; 
    }
    
    public static void updateUser(User updatedUser) {
    	JsonThings.updateUserInFile(updatedUser);
    }
    }
   


