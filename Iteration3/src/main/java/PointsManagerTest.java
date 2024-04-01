
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointsManagerTest {
	
    private ArrayList<User> users;
    private PointsManager manager;
	
	 @BeforeEach
	 void setUp() {
	   users = new ArrayList<>();
	   manager = new PointsManager(users);
	 }
	
	@Test
	void isNotEmpty() {
		User user1 = new User();
		users.add(user1);
		assertFalse("User list is empty.", users.isEmpty());
	}
	
	@Test
	void initPtsToZero() {
		User user1 = new User();
		users.add(user1);
		assertEquals("Points aren't initalized to 0.", 0, user1.getPoints());
		}
	
	@Test
	void setPointsTest1() {
		User user1 = new User();
		user1.setPoints(0);
		assertEquals("Points aren't set to 0.", 0, user1.getPoints());
	}
	
	@Test
	void setPointsTest2() {
		User user1 = new User();
		user1.setPoints(-284621722);
		assertEquals("Points aren't set to 0.", 0, user1.getPoints());
	}
	
	@Test
	void countUsers() {
		for (int i = 0; i < 5; i++) {
			users.add(new User());
		}
		assertEquals("There aren't 5 users registered.", 5, users.size());
	}
	
	
	
	@Test
	void idExistsExceptionTest() throws IdExistsException {
		User user1 = new User("123");
		
		assertDoesNotThrow(() -> manager.addUser(user1));

        User user2 = new User("123");
        IdExistsException thrown = assertThrows(
                IdExistsException.class,
                () -> manager.addUser(user2), "Expected to throw exception, "
                		+ "but it didn't."
        );

        assertTrue(thrown.getMessage().contains("ID already exists!"));
	}
	
	@Test
	void addPointsTest1() throws IdExistsException {
		User user1 = new User();
		manager.addUser(user1);
		
		manager.addPoints(user1, 11.00);
		
		assertEquals("Did not add points or add right amount.",
				3, user1.getPoints());	
	}
	
	@Test
	void addPointsTest2() throws IdExistsException {
		User user1 = new User();
		manager.addUser(user1);
		
		manager.addPoints(user1, 8.00);
		manager.addPoints(user1, 17.00);
		
		
		assertEquals("Did not add points or add right amount.",
				6, user1.getPoints());	
	}
	
	@Test
	void addPointsTest3() throws IdExistsException{
		for (int i = 0; i < 5; i++) {
	        manager.addUser(new User());
	        manager.addPoints(users.get(i), 10 * i);
	        assertEquals("User" + i + " has incorrect or no points.",
	                2 * i, users.get(i).getPoints());
	        
	    }
	    
	    assertEquals("Did not add users correctly", 5, this.users.size());
	}
	
	@Test
	void redeemPointsTest1() throws IdExistsException, NoPointsException {
		User user1 = new User();
		manager.addUser(user1);
		
		manager.addPoints(user1, 82.27);
		
		assertEquals("Did not add points or add right amount.",
				17, user1.getPoints());
		
		manager.redeemPoints(user1, 12.58);
		
		
		assertEquals("Did not redeem points or redeem right amount.",
				5, user1.getPoints());	
	}
	
	@Test
	void redeemPointsTest2() throws IdExistsException, NoPointsException {
		User user1 = new User();
		manager.addUser(user1);
		
		manager.addPoints(user1, 40.00);
		
		assertEquals("Did not add points or add right amount.",
				8, user1.getPoints());
		
		manager.redeemPoints(user1, 9.14);
		
		
		assertEquals("Did not redeem points or redeem right amount.",
				0, user1.getPoints() );	
		
	}
	
	@Test
	void redeemPointsTest3() throws IdExistsException, NoPointsException {
		User user1 = new User();
		manager.addUser(user1);
		
		manager.addPoints(user1, 82.27);
		
		assertEquals("Did not add points or add right amount.",
				17, user1.getPoints());
		
		manager.redeemPoints(user1, 16.99);
		
		
		assertEquals("Did not redeem points or redeem right amount.",
				1, user1.getPoints());	
	}
	
	@Test
	void redeemPointsTest4() throws IdExistsException, NoPointsException {
		
		User user1 = new User();

		manager.addPoints(user1, 12.94);
		
		assertEquals("Did not add points or add right amount.",
				3, user1.getPoints());
		
		manager.redeemPoints(user1, 3);
		
		assertEquals("Did not redeem points or redeem right amount.",
				0, user1.getPoints());	
			
	}
	
	@Test
	void redeemPointsTest5() throws IdExistsException, NoPointsException {
		
		User user1 = new User();

		manager.addPoints(user1, 17.13);
		
		assertEquals("Did not add points or add right amount.",
				4, user1.getPoints());
		
		manager.redeemPoints(user1, 4.01);
		
		assertEquals("Did not redeem points or redeem right amount.",
				0, user1.getPoints());	
			
	}
	
	
	@Test
	void redeemPointsTest6() throws IdExistsException, NoPointsException {
		for (int i = 0; i < 5; i++) {
	        manager.addUser(new User());
	        manager.addPoints(this.users.get(i), 5 * i + 10); // [2, 3, 4, 5, 6]
	        assertEquals("User" + i + " has incorrect or no points.",
	                i + 2, this.users.get(i).getPoints());
	        
	    }
	    assertEquals("Did not add users correctly", 5, users.size());

	    for (int i = 0; i < 5; i++) {
	        manager.redeemPoints(this.users.get(i), 1.48);
	        assertEquals("User" + i + " did not redeem points or redeem right amount.",
	                i + 1, this.users.get(i).getPoints());
	    }
	}
		
	
	
	@Test
	void NoPointsExceptionTest() throws IdExistsException, NoPointsException {
		for (int i = 0; i < 5; i++) {
	        manager.addUser(new User());
	        manager.addPoints(users.get(i), 10 * i);
	        assertEquals("User" + i + " has incorrect or no points.",
	                2 * i, users.get(i).getPoints());
	    }
	    
	    assertEquals("Did not add users correctly", 5, this.users.size());
	   
	    NoPointsException thrown = assertThrows(
	            NoPointsException.class,
	            () -> manager.redeemPoints(users.get(0), 0), 
	            "Expected to throw NoPointsException, but it didn't.");   
	    assertTrue(thrown.getMessage().contains("You have no points!"), 
	    		"Exception message does not contain expected text.");
	    }

	
	
	// 4, 6, 
	

}

