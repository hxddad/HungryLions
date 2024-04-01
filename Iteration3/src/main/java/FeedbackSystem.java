
import java.util.ArrayList;
import java.util.List;

public class FeedbackSystem {
    //similar to the review System where it checks for offensive language and word limit as well as if there are no reviews
	private List<Feedback> reviews;
    private List<String> flaggedWords;
    private static final int WORD_LIMIT = 20;

    public FeedbackSystem() {
       //instantiates everything and I have started by just using 3 words as there are way too many offensive words 
    	this.reviews = new ArrayList<>();
        this.flaggedWords = new ArrayList<>();
        this.flaggedWords.addAll(List.of("abuse", "suicide", "kill"));
    }

    public void submitFeedback(Feedback feedback) throws SubmissionException {
       
    	//in order for the review to be in the data base it has to be within the word limit, no flagged words and not be empty 
    	if (isContentAppropriate(feedback) && isWithinWordLimit(feedback)&&isEmpty(feedback)) {
            reviews.add(feedback);
            System.out.println("Feedback submitted successfully!");
        } 
    	//if it does not pass any one of those then it will return an exception
    	else {
            throw new SubmissionException("Feedback is offensive.");
        }
    }

    private boolean isContentAppropriate(Feedback feedback) {
      
    //this checks to see if the three feedbacks have any flagged words through the containsFlaggedWord method	
    	if(!containsFlaggedWord(feedback.anybugs) && !containsFlaggedWord(feedback.newfeaturesreq) && !containsFlaggedWord(feedback.critics)) {
        	return true;
        }
        return false;	
    }

    private boolean containsFlaggedWord(String text) {
        //this method compares all the string values to make sure it does not match any words listed as flagged 
    	for (String word : flaggedWords) {
            if (text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private boolean isWithinWordLimit(Feedback feedback) {
        //this checks the word limit through the wordcount method and it has to be equal to or below 20 words
    	if(wordCount(feedback.anybugs) <= WORD_LIMIT && wordCount(feedback.newfeaturesreq) <= WORD_LIMIT && wordCount(feedback.critics) <= WORD_LIMIT) {
    	return true;	
    	}
    	return false;
    }
    private boolean isEmpty(Feedback feedback) {
 	 //this just makes sure that the whole feedback is not empty 
    	
    	if(feedback.anybugs.equals("")||feedback.critics.equals("")||feedback.newfeaturesreq.equals("")) {
 		   return false;
 	   }
 	   return true;
    }
    private int wordCount(String text) {
       //this splits the string value by letters and grabs its length
    	return text.split("\\s+").length;
    }
    
    public static class Feedback {
     //this is simply the attributes and constructer of Feedback class   
    	
    	String anybugs;
        String newfeaturesreq;
        String critics;

        public Feedback(String anybugs, String newfeaturesreq, String critics) {
            this.anybugs = anybugs;
            this.newfeaturesreq = newfeaturesreq;
            this.critics = critics;
        }

    }
    
   //this is the Exception class we are using 

    public static class SubmissionException extends Exception {
        public SubmissionException(String message) {
            super(message);
        }
    }
}
