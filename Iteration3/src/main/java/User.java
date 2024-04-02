

import java.util.UUID;

public class User {
	
	private final String id;
	private int points;
	
	public User() {
		super();
		this.id = UUID.randomUUID().toString().substring(0,6);
		this.points = 0;
	}
	
	public User(String id) {
		super();
		this.id = id;
	}
	

	void setPoints(int points) {
		if (points <= 0) {
			this.points = 0;
		}
		else {
			this.points = points;
		}
	}

	public String getId() {
		return id;
	}

	public int getPoints() {
		return points;
	}
	
	
	
	
	
	


}
