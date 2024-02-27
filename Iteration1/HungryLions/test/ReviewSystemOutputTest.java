package ReviewSys;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ReviewSystemOutputTest {
	
	 private ReviewSystemFunctions func;
	    @BeforeEach
	    public void setUp() {
	     func = new ReviewSystemFunctions();
	    }
	@Test
	
	public void testSubmitValidReview() throws SubmissionException {
		
		func = new ReviewSystemFunctions();
		 ReviewSystem review = new ReviewSystem("Ryuga", "Good food and kind of okay noodles", 5, 5, 4, 4, 2, 3, "boom");
		int expected = 1;
		func.submitReview(review);
		assertEquals("should only contain 1",expected,func.getReviews().size());
		
	}
  @Test
	public void testSubmitReviewWithInvalidRating() {
   
	
	ReviewSystem review = new ReviewSystem("User", "Review text", 6, 5, 4, -7, 1, 2, "boom");
  
   assertThrows(SubmissionException.class, () -> {func.submitReview(review);});
	
}
  
  @Test
  public void testWordLimitExceeded() {
      String textexceedswordlimit = "it was  pretty good i enjoyed everything about it especially the chef was great everyone was amaing ,but food didnt look that good tho ngl"; 
      ReviewSystem ReviewToolong = new ReviewSystem("jeff chan", textexceedswordlimit, 4, 4, 4, 4, 1, 5, "bbom");
      assertThrows(SubmissionException.class, () -> func.submitReview(ReviewToolong));
  }

  @Test
  public void testFlaggedContentSubmission() {
      func.flagReview("damn"); 
      ReviewSystem flagged = new ReviewSystem("UserFlagged", "damn this place bad", 3, 3, 3, 3, 2, 2, "boom");
      assertThrows(SubmissionException.class, () -> func.submitReview(flagged));
  }

  @Test
  public void testValidLowerBoundaryRatings() throws SubmissionException {
      ReviewSystem toolow = new ReviewSystem("blazer", "hated it ", -1, 1, 1, -1, 1, -1, "boom");
      assertThrows(SubmissionException.class, () -> func.submitReview(toolow));
  }

  @Test
  public void testInvalidTimesGone() {
      ReviewSystem invalidnumtoohigh = new ReviewSystem("blaster", "loved it ", 4, 4, 4, 6, -1, 3, "bbom");
      assertThrows(SubmissionException.class, () -> func.submitReview(invalidnumtoohigh));
  }
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
