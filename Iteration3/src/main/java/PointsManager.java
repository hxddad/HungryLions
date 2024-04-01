
import java.util.ArrayList;

public class PointsManager {
	
	private ArrayList<User> users = new ArrayList<>();

	protected PointsManager(ArrayList<User> users) {
		super();
		this.users = users;
	}
	

	public ArrayList<User> getUsers() {
		return users;
	}


	private boolean userIdExists(String id) {
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
		return false;
	}
	
	public void addUser(User user) throws IdExistsException {	
		if (userIdExists(user.getId())) {
			throw new IdExistsException("ID already exists!");
		}
		this.users.add(user);
	}
	
	
	public void addPoints(User user, double mealCost) {
		double newPoints = mealCost * 0.2 + user.getPoints();
		user.setPoints((int) Math.ceil(newPoints));
		}
	
	public void redeemPoints(User user, double mealCost) throws NoPointsException{
		if (user.getPoints() == 0) {
			throw new NoPointsException("You have no points!");
		}
		
		else if (user.getPoints() - mealCost < 0) {
			user.setPoints(0);
		}
		
		else {
		
		user.setPoints((int) Math.ceil(user.getPoints() - mealCost));
		}
	}

	
	
	

	
}
