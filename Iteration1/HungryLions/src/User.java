package Main;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class User {
    private String username;
    private String password;
    private List<String> dietaryRestrictions;
    private String campusAffiliation;
    private boolean mealPlanParticipation;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.dietaryRestrictions = new ArrayList<>();
        this.campusAffiliation = "";
        this.mealPlanParticipation = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            dietaryRestrictions.add(jsonArray.getString(i));
        }
    }

    public String getCampusAffiliation() {
        return campusAffiliation;
    }

    public void setCampusAffiliation(String campusAffiliation) {
        this.campusAffiliation = campusAffiliation;
    }

    public boolean isMealPlanParticipation() {
        return mealPlanParticipation;
    }

    public void setMealPlanParticipation(boolean mealPlanParticipation) {
        this.mealPlanParticipation = mealPlanParticipation;
    }
}
