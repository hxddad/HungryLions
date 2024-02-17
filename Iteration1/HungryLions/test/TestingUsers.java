package Main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestingUsers {
	private static final String JSON_FILE_PATH = "test_users.json";

    @BeforeEach
    void setUp() {
        // Clear the test file before each test
        clearTestFile();
    }

    @Test
    void testWriteUsersToFile() {
        // Create some test users
        User user1 = new User("user1", "password1");
        user1.setCampusAffiliation("Campus A");
        user1.setMealPlanParticipation(true);
        user1.getDietaryRestrictions().addAll(Arrays.asList("Vegan", "Halal"));

        User user2 = new User("user2", "password2");
        user2.setCampusAffiliation("Campus B");
        user2.setMealPlanParticipation(false);
        user2.getDietaryRestrictions().addAll(Arrays.asList("Vegetarian", "Gluten-Free"));

        // Write test users to file
        JsonThings.writeUsersToFile(List.of(user1, user2));

        // Read users from file and check if they are written correctly
        List<User> usersFromFile = JsonThings.readUsersFromFile();
        assertEquals(2, usersFromFile.size());

        User userFromFile1 = usersFromFile.get(0);
        assertEquals("user1", userFromFile1.getUsername());
        assertEquals("password1", userFromFile1.getPassword());
        assertEquals("Campus A", userFromFile1.getCampusAffiliation());
        assertTrue(userFromFile1.isMealPlanParticipation());
        assertEquals(2, userFromFile1.getDietaryRestrictions().size());
        assertTrue(userFromFile1.getDietaryRestrictions().containsAll(Arrays.asList("Vegan", "Halal")));

        User userFromFile2 = usersFromFile.get(1);
        assertEquals("user2", userFromFile2.getUsername());
        assertEquals("password2", userFromFile2.getPassword());
        assertEquals("Campus B", userFromFile2.getCampusAffiliation());
        assertFalse(userFromFile2.isMealPlanParticipation());
        assertEquals(2, userFromFile2.getDietaryRestrictions().size());
        assertTrue(userFromFile2.getDietaryRestrictions().containsAll(Arrays.asList("Vegetarian", "Gluten-Free")));
    }


    // Helper method to clear the test file
    private void clearTestFile() {
        TestUtils.writeToFile(JSON_FILE_PATH, "");
    }
    public class TestUtils {

        public static void writeToFile(String filePath, String content) {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
