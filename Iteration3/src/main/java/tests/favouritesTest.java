package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import favourites;

class favouritesTest {
	
	
	favourites favorite;

	@BeforeEach
	void BeforeEach() {
		favorite = new favourites();
	}

	@AfterEach
	void AfterEach() {
		favorite.close();
	}
	
	
	// Add to favourites
	@Test
	void test1() throws SQLException {
		boolean test = favorite.addFavorites("Tim Hortons", "testuser547");
		assertEquals(true, test);
		favorite.removeFavorites("Tim Hortons", "testuser547");
		
	}
	
	
	// Adding when already exists
	@Test
	void test2() throws SQLException {
		favorite.addFavorites("Tim Hortons", "testuser547");
		boolean test = favorite.addFavorites("Tim Hortons", "testuser547");
		assertEquals(false, test);
		
	}
	
	
	// Get Favorites
	@Test
	void test3() throws SQLException {
		String test = favorite.getFavorites("testuser547");
		assertEquals("Tim Hortons", test);
		
	}
	
	// Remove Favorites (Valid) 
	@Test
	void test4() throws SQLException {
		favorite.addFavorites("Tim Hortons", "testuser547");
		boolean test = favorite.removeFavorites("Tim Hortons","testuser547");
		
		assertEquals(true, test);
		
	}
	
	// Remove Favorites (Invalid)
	@Test
	void test5() throws SQLException {
		boolean test = favorite.removeFavorites("Tim Hortons", "testuser547");
		assertEquals(false, test);
	}
	
	// Get invalid Favorites from invalid user
	@Test
	void test6() throws SQLException {
		String test = favorite.getFavorites("testuser999");
		assertEquals("", test);
	}
	
	

}
