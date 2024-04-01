package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import userdb;

class userdbTest {
	
	
	userdb db;
	
	@BeforeEach
	void BeforeEach() {
		db = new userdb();
	}
	
	
	// Add New User
	@Test
	void test1() throws SQLException {
		byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		boolean test = db.addUser(generatedString, generatedString);
		assertEquals(true, test);
		
	}
	
	// Add Existing User
	@Test
	void test2() throws SQLException {
		boolean test = db.addUser("wilsonepic21", "wilsonepic21");
		assertEquals(false, test);
		
	}
	
	// Authenticate User
	@Test
	void test3() throws SQLException {
		boolean test = db.verifyPassword("wilsonepic21", "wilsonepic21");
		assertEquals(true, test);
		
	}
	// Authenticate User (Fail)
	@Test
	void test4() throws SQLException {
		boolean test = db.verifyPassword("wilsonepic21", "asdasdasd");
		assertEquals(false, test);
		
	}
	
	

}
