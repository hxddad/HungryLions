package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonThings {
    private static final String JSON_FILE_PATH = "users.json";

    public static void writeUsersToFile(List<User> users) {
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject userJson = new JSONObject();
            try {
            userJson.put("username", user.getUsername());
            userJson.put("password", user.getPassword());
            userJson.put("dietary_restrictions", user.getDietaryRestrictions());
            userJson.put("campus_affiliation", user.getCampusAffiliation());
            userJson.put("meal_plan_participation", user.isMealPlanParticipation());
            jsonArray.put(userJson);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        }

        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(jsonArray.toString(2)); // Indent JSON for better readability
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userJson = jsonArray.getJSONObject(i);
                User user = new User(
                        userJson.getString("username"),
                        userJson.getString("password")
                );
                user.setDietaryRestrictions(userJson.getJSONArray("dietary_restrictions"));
                user.setCampusAffiliation(userJson.getString("campus_affiliation"));
                user.setMealPlanParticipation(userJson.getBoolean("meal_plan_participation"));
                users.add(user);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static void addUserToFile(User newUser) {
        try {
            // Read existing user data from JSON file
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray jsonArray = new JSONArray(jsonString);

            // Convert the newUser object to JSON
            JSONObject newUserJson = new JSONObject();
            newUserJson.put("username", newUser.getUsername());
            newUserJson.put("password", newUser.getPassword());
            newUserJson.put("dietary_restrictions", new JSONArray(newUser.getDietaryRestrictions()));
            newUserJson.put("campus_affiliation", newUser.getCampusAffiliation());
            newUserJson.put("meal_plan_participation", newUser.isMealPlanParticipation());

            // Add the newUser JSON object to the array
            jsonArray.put(newUserJson);

            // Write the updated JSON array back to the file
            try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
                fileWriter.write(jsonArray.toString(2)); // Indent JSON for better readability
                System.out.println("User added successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateUserInFile(User updatedUser) {
        try {
            // Read existing user data from JSON file
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray jsonArray = new JSONArray(jsonString);

            // Iterate over the array to find and update the user
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userJson = jsonArray.getJSONObject(i);
                if (userJson.getString("username").equals(updatedUser.getUsername())) {
                    // Update user's data in the JSON object
                    userJson.put("password", updatedUser.getPassword());
                    userJson.put("dietary_restrictions", new JSONArray(updatedUser.getDietaryRestrictions()));
                    userJson.put("campus_affiliation", updatedUser.getCampusAffiliation());
                    userJson.put("meal_plan_participation", updatedUser.isMealPlanParticipation());

                    // Write the updated JSON object back to the array
                    jsonArray.put(i, userJson);

                    // Write the modified JSON array to the file
                    try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
                        fileWriter.write(jsonArray.toString(2)); // Indent JSON for better readability
                        System.out.println("User updated successfully!");
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("User not found."); // If the user is not found
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

