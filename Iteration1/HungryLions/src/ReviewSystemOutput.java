
package ReviewSys;
import java.util.*;
class ReviewSystem {
   String username;
   String reviewWords;
   int foodQuality;
   int service;
   int atmosphere;
   int valueForMoney;
   int howmanyTimesGone;
   int howManyFoodItemsEaten;
   String restaurantName;
   
   public ReviewSystem(String username, String reviewWords, int foodQuality, int service, int atmosphere, int valueForMoney,int howmanyTimesGone,int howManyFoodItemsEaten,String restaurantName) {
       this.username = username;
       this.reviewWords = reviewWords;
       this.foodQuality = foodQuality;
       this.service = service;
       this.atmosphere = atmosphere;
       this.valueForMoney = valueForMoney;
       this.howmanyTimesGone= howmanyTimesGone;
       this.howManyFoodItemsEaten = howManyFoodItemsEaten;
       this.restaurantName = restaurantName;
   }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReviewWords() {
		return reviewWords;
	}
	public void setReviewWords(String reviewWords) {
		this.reviewWords = reviewWords;
	}
	public int getFoodQuality() {
		return foodQuality;
	}
	public void setFoodQuality(int foodQuality) {
		this.foodQuality = foodQuality;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}
	public int getAtmosphere() {
		return atmosphere;
	}
	public void setAtmosphere(int atmosphere) {
		this.atmosphere = atmosphere;
	}
	public int getValueForMoney() {
		return valueForMoney;
	}
	public void setValueForMoney(int valueForMoney) {
		this.valueForMoney = valueForMoney;
	}
	public int getHowmanyTimesGone() {
		return howmanyTimesGone;
	}
	public void setHowmanyTimesGone(int howmanyTimesGone) {
		this.howmanyTimesGone = howmanyTimesGone;
	}
	public int getHowManyFoodItemsEaten() {
		return howManyFoodItemsEaten;
	}
	public void setHowManyFoodItemsEaten(int howManyFoodItemsEaten) {
		this.howManyFoodItemsEaten = howManyFoodItemsEaten;
	}
	
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String RestaurantName() {
		return restaurantName;
	}
	
	
 }
class ReviewSystemFunctions {
   private List<ReviewSystem> reviews;
   private List<String> flagged;
   private static final int WORD_LIMIT = 20;
   ReviewSystemFunctions() {
       this.reviews = new ArrayList<>();
       this.flagged = new ArrayList<>();
   }
   public void submitReview(ReviewSystem review) throws SubmissionException {
       if (moderationCheck(review.reviewWords) && wordLimit(review.reviewWords)&& ValidRating(review.foodQuality) && ValidRating(review.service)&& ValidRating(review.atmosphere) && ValidRating(review.valueForMoney)&& FormatCheck(review.reviewWords)&&TimesGone(review.howmanyTimesGone)&&numOfmenuItemsEaten(review.howManyFoodItemsEaten)) {
           reviews.add(review);
           System.out.println("Review submitted successfully!");
           displayReviewDetails(review);
       } else {
           throw new SubmissionException("Review flagged as inappropriate, exceeds word limit, or contains invalid ratings. Not submitted.");
       }
   }
  
   private boolean FormatCheck(String reviewWords) {
   	
   	String[] reviewArray = reviewWords.split("\\s+");
   	
   	 for (String word : reviewArray) {
         
   		 if (word.equals(word.toLowerCase())) {
                return true;
            }
        }
       
        return false;
   	
    }
   	
   private boolean	TimesGone(int timesGone) {
   	
   	if(timesGone==0||timesGone<0) {
   		return false;
   	}
   	else {
   		return true;
   	}
   	
   }
  
   private boolean numOfmenuItemsEaten(int foodItems) {
   	
   if(foodItems==0||foodItems<0)	{
   	return false;
   }
   else {
   	return true;
   }
   	
   	
   }
  
   private boolean moderationCheck(String reviewWords) {
 
       for (String flaggedPart : flagged) {
           if (reviewWords.toLowerCase().contains(flaggedPart.toLowerCase())) {
               return false;
           }
       }
       return true;
   }
   private boolean wordLimit(String reviewWords) {
       if( reviewWords.split("\\s+").length <= WORD_LIMIT) {
       	return true;
       }
       	else {
       		return false;
       	}
      
   }
   private boolean ValidRating(int rating) throws SubmissionException{
     
   	if(rating>=1 && rating <=5) {
   		return true;
   	}
   	else {
   	
      throw new SubmissionException("invalid Rating");
   	}
   }
   public void flagReview(String NotAllowedwords) {
       flagged.add(NotAllowedwords);
       System.out.println("Not Appropriate comment ");
   }
   public List<ReviewSystem> getReviews() {
       return reviews;
   }
private void displayReviewDetails(ReviewSystem review) {
   System.out.println("Review Details:");
   System.out.println("Username: " + review.username);
   System.out.println("Review Words: " + review.reviewWords);
   System.out.println("Food Quality: " + review.foodQuality);
   System.out.println("Service: " + review.service);
   System.out.println("Atmosphere: " + review.atmosphere);
   System.out.println("Value for Money: " + review.valueForMoney);
   System.out.println("Times person went to the restaurant: " + review.howmanyTimesGone);
   System.out.println("Number of menu items eaten: " + review.howManyFoodItemsEaten);
   System.out.println("Restaurant Name: " + review.restaurantName);
}
public void displayAllReviews() {
   System.out.println("All Reviews:");
   for (ReviewSystem review : reviews) {
       displayReviewDetails(review);
   }
}
public List<String> getFlagged() {
	return flagged;
}
public void setFlagged(List<String> flagged) {
	this.flagged = flagged;
}
public static int getWordLimit() {
	return WORD_LIMIT;
}
public void setReviews(List<ReviewSystem> reviews) {
	this.reviews = reviews;
}
}
class SubmissionException extends Exception {
   public SubmissionException(String m) {
       super(m);
   }
}
public class ReviewSystemOutput {
   public static void main(String[] args) {
     
   	
}
   	
   	
} 	
    	
    	
    	
    	
    	
    	
   
