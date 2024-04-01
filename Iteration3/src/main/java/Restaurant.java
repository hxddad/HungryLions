public class Restaurant {
    private int id;
    private String name;
    private String location;
    private String cuisine;
    private String paymentMethod;
    private String dietaryOptions;
    private String priceRange;

    public Restaurant(int id, String name, String location, String cuisine, String paymentMethod, String dietaryOptions, String priceRange) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.paymentMethod = paymentMethod;
        this.dietaryOptions = dietaryOptions;
        this.priceRange = priceRange;
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
    
}
