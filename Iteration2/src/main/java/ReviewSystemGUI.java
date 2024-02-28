import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReviewSystemGUI extends JFrame {
    
    private JComboBox<String> restaurantComboBox;
    private Map<String, Integer> restaurantMap = new HashMap<>(); 
    private JTextField reviewWords;
    private JTextField foodQuality;
    private JTextField service;
    private JTextField atmosphere;
    private JTextField valueForMoney;
    private JTextField timesGone;
    private JTextField itemsEaten;
    private JButton submitButton;
    private String selectedRestaurant;

    public ReviewSystemGUI(String selectedRestaurant) {
        this.selectedRestaurant = selectedRestaurant; 
        initializeGUIComponents();
        populateRestaurants();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public ReviewSystemGUI() {
        this(null); 
    }
    
    private void initializeGUIComponents() {
        setLayout(new GridLayout(0, 2));

 

        add(new JLabel("Restaurant Name:"));
        restaurantComboBox = new JComboBox<>(); 
        add(restaurantComboBox);

        add(new JLabel("Review Words:"));
        reviewWords = new JTextField(20);
        add(reviewWords);

        add(new JLabel("Food Quality (1-5):"));
        foodQuality = new JTextField(20);
        add(foodQuality);

        add(new JLabel("Service (1-5):"));
        service = new JTextField(20);
        add(service);

        add(new JLabel("Atmosphere (1-5):"));
        atmosphere = new JTextField(20);
        add(atmosphere);

        add(new JLabel("Value for Money (1-5):"));
        valueForMoney = new JTextField(20);
        add(valueForMoney);

        add(new JLabel("Times Gone:"));
        timesGone = new JTextField(20);
        add(timesGone);

        add(new JLabel("Items Eaten:"));
        itemsEaten = new JTextField(20);
        add(itemsEaten);

        submitButton = new JButton("Submit Review");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReview();
            }
        });
        add(submitButton);

        setSize(500, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void populateRestaurants() {
        String sql = "SELECT ID, RestaurantName FROM restaurants"; 
        try (Connection conn = DatabaseConnection.connect("app");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("RestaurantName");
                int id = rs.getInt("ID");
                restaurantComboBox.addItem(name);
                restaurantMap.put(name, id);
            }
            if (selectedRestaurant != null && restaurantMap.containsKey(selectedRestaurant)) {
                restaurantComboBox.setSelectedItem(selectedRestaurant); // Pre-select the given restaurant
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load restaurants: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitReview() {
        try {
            String selectedRestaurantName = (String) restaurantComboBox.getSelectedItem();
            Integer restaurantId = restaurantMap.get(selectedRestaurantName);
            int userId = 1; // Hardcoded user ID
            int foodQualityRating = Integer.parseInt(foodQuality.getText());
            int serviceRating = Integer.parseInt(service.getText());
            int atmosphereRating = Integer.parseInt(atmosphere.getText());
            int valueForMoneyRating = Integer.parseInt(valueForMoney.getText());
            int timesGoneToInt = Integer.parseInt(timesGone.getText());
            int itemsEatenCount = Integer.parseInt(itemsEaten.getText());
            String reviewText = reviewWords.getText();
            
            ReviewSystem review = new ReviewSystem(restaurantId, userId, reviewText, foodQualityRating, serviceRating, atmosphereRating, valueForMoneyRating, timesGoneToInt, itemsEatenCount);
            ReviewSystemOutput.insertReview(review);
            JOptionPane.showMessageDialog(this, "Review submitted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for ratings, times gone, and items eaten.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to submit review: " + e.getMessage(), "Submission Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReviewSystemGUI::new);
    }
}

