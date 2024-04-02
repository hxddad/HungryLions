public class ReviewSystem {
    private int restaurantId;
    private int userId;
    private String reviewWords;
    private int foodQuality;
    private int service;
    private int atmosphere;
    private int valueForMoney;
    private int timesGone;
    private int itemsEaten;

    public ReviewSystem(int restaurantId, int userId, String reviewWords, int foodQuality, int service, int atmosphere, int valueForMoney, int timesGone, int itemsEaten) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.reviewWords = reviewWords;
        this.foodQuality = foodQuality;
        this.service = service;
        this.atmosphere = atmosphere;
        this.valueForMoney = valueForMoney;
        this.timesGone = timesGone;
        this.itemsEaten = itemsEaten;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public String getReviewWords() {
        return reviewWords;
    }

    public int getFoodQuality() {
        return foodQuality;
    }

    public int getService() {
        return service;
    }

    public int getAtmosphere() {
        return atmosphere;
    }

    public int getValueForMoney() {
        return valueForMoney;
    }

    public int getHowmanyTimesGone() {
        return timesGone;
    }

    public int getHowManyFoodItemsEaten() {
        return itemsEaten;
    }

  
}
