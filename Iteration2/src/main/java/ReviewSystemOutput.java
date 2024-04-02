import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewSystemOutput {

    public static void insertReview(ReviewSystem review) {
        String sql = "INSERT INTO reviews(restaurant_id, user_id, foodQuality, review_text, service, atmosphere, valueForMoney, timesGone, numberOfItemsAte) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.connect("review"); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getRestaurantId()); 
            pstmt.setInt(2, review.getUserId()); 
            pstmt.setInt(3, review.getFoodQuality());
            pstmt.setString(4, review.getReviewWords());
            pstmt.setInt(5, review.getService());
            pstmt.setInt(6, review.getAtmosphere());
            pstmt.setInt(7, review.getValueForMoney());
            pstmt.setInt(8, review.getHowmanyTimesGone());
            pstmt.setInt(9, review.getHowManyFoodItemsEaten());
            pstmt.executeUpdate();
            System.out.println("A new review has been inserted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
