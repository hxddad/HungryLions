import java.util.ArrayList;
import java.util.List;

public class ChallengesManager {
    private List<Challenge> challenges;

    public ChallengesManager() {
        challenges = new ArrayList<>();
    }

    public void addMonthlyChallenges() {
    	   challenges.add(new Challenge("Mr. Worldwide", "Purchase food worth at least"
                   + " $10 at cultural restaruants 50 times for "
                   + "a free a meal.", 0, 50));
           challenges.add(new Challenge("750 Hungry Cubs", "Spend a total of "
                   + "$750 worth of food for 75% off of a meal.", 0, 750));
           challenges.add(new Challenge("Why's this line so long?", "Purchase 25 times at any "
                   + "Tim Horton's on campus for $3 off an order.", 0, 25));
           challenges.add(new Challenge("Taste of Asia", "Purchace food worth at "
                   + "least $10 at Chinese, Korean, Japanese, "
                   + "Thai or Indian dining locations 30 times for a 50% off of a meal.", 0, 30));
           challenges.add(new Challenge("In My Lane", "Purchace food worth at "
                   + "least $10 at 20 dining locations at York Lanes for 25%"
                   + " OFF of a meal.", 0, 20));
           challenges.add(new Challenge("King of the Jungle", "Purchace food at "
           		+ "least $10 at dining locations 100 times on campus for 100 free points.", 0, 100));
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
