
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeedbackSystemOutputTest {

	//just get the attribute as the class we are testing
    private FeedbackSystem feedbackSystem;

    @BeforeEach
    void setUp() {
       //just equating it to a new feedbacksystem class
    	feedbackSystem = new FeedbackSystem();
       
    }

    @Test
    void testSubmitFeedbackSuccess() {
      
    	//making the feedback system class have words and no mistakes to test if it submits  
        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback("Nah we good", "Add something to make life easy", "No criticism");

        assertDoesNotThrow(() -> feedbackSystem.submitFeedback(feedback), "submission should throw nothing");
    }

    @Test
    void testSubmitFeedbackWithFlaggedWord() {
     //Adding the flagged word kill to see if the exception will be thrown  
        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback("flaggedword kill here", "Add feature Y", "No criticism");

        assertThrows(FeedbackSystem.SubmissionException.class, () -> feedbackSystem.submitFeedback(feedback), "Feedback containing flagged words should throw SubmissionException");
    }
    @Test
    void testSubmitFeedbackWithFlaggedWord2() {
     //Adding the flagged word kill to see if the exception will be thrown  
        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback("flaggedword  here", "Add feature kill Y", "No criticism");

        assertThrows(FeedbackSystem.SubmissionException.class, () -> feedbackSystem.submitFeedback(feedback), "Feedback containing flagged words should throw SubmissionException");
    }
    
    @Test
    void testSubmitFeedbackWithFlaggedWord3() {
     //Adding the flagged word kill to see if the exception will be thrown  
        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback("flaggedword kill here", "Add feature Y", "No criticism kill");

        assertThrows(FeedbackSystem.SubmissionException.class, () -> feedbackSystem.submitFeedback(feedback), "Feedback containing flagged words should throw SubmissionException");
    }
    
     
    
    @Test
    void testSubmitFeedbackExceedingWordLimit() {
       //making the sentence longer than 20 words to see if it will throw an exception
        String longString = "This is a very long string that exceeds the word limit set by the feedback system for testing purposes and will not work .";
        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback(longString, "Add feature Z", "No criticism");

        assertThrows(FeedbackSystem.SubmissionException.class, () -> feedbackSystem.submitFeedback(feedback), "Feedback exceeding word limit should throw SubmissionException");
    }
}
	    

