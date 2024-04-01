
import java.util.*;
import java.util.concurrent.*;

class Challenge {
    private String name;
    private String description;
    private int progress;
    private int goal;
    
    

    public Challenge(String name, String description, 
    	int progress, int goal) {
        this.name = name;
        this.description = description;
        this.progress = progress;
        this.goal = goal;
    }

    public int getProgress() {
		return progress;
	}

	public int getGoal() {
		return goal;
	}

	public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}



