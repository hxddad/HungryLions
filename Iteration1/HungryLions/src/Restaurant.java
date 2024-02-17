public class Restaurant {
    private int id;
    private String name;
    private String location;
    private String cuisine;
    private String paymentMethod;
    private String dietaryOptions;
    private String priceRange;
    
    private boolean isHalal;
    private boolean isGlutenFree;
    private boolean isVegan;
    private boolean isVegetarian;

    public Restaurant(int id, String name, String location, String cuisine, String paymentMethod, String dietaryOptions, String priceRange) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.paymentMethod = paymentMethod;
        this.dietaryOptions = dietaryOptions;
        this.priceRange = priceRange;
        
        this.isHalal = dietaryOptions.contains("Halal");
        this.isGlutenFree = dietaryOptions.contains("No Gluten");
        this.isVegan = dietaryOptions.contains("Vegan");
        this.isVegetarian = dietaryOptions.contains("Vegetarian");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getDietaryOptions() {
        return dietaryOptions;
    }

    public String getPriceRange() {
        return priceRange;
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
