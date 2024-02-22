import java.util.*;
public class Favourites {
    private ArrayList<String> favorites;
    private String username;


    public Favourites() {
        username = "";
        favorites = new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }

    public ArrayList<String> getFavorites() {
        return this.favorites;
    }

    public void addFavorites(String favorite) {
        favorites.add(favorite);
    }

    public boolean removeFavorites(String favorites) {
        if (!this.favorites.contains(favorites)) return false;

        this.favorites.remove(favorites);
        return true;
    }

}
