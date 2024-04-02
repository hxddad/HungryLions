public class DietaryFilter {
    private boolean isHalal;
    private boolean isGlutenFree;
    private boolean isVegan;
    private boolean isVegetarian;

    public DietaryFilter(Restaurant name) {
    	this.isHalal = name.getDietaryOptions().contains("Halal");
        this.isGlutenFree = name.getDietaryOptions().contains("No Gluten");
        this.isVegan = name.getDietaryOptions().contains("Vegan");
        this.isVegetarian = name.getDietaryOptions().contains("Vegetarian");
    }

    public boolean isHalal() {
        return isHalal;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
}
