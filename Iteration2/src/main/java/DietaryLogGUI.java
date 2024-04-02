//package DietaryLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DietaryLogGUI extends JFrame {
    private List<String> foodItems;
    private JTextField foodItemInput;
    private JTextArea foodItemDisplay;
    private Connection connection;
	private Statement statement;
	public String username;
	
    public DietaryLogGUI(String username) throws SQLException {
    	this.username=username;
    	try {
			connection = DatabaseConnection.connect("log");
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
    	
        foodItems = new ArrayList<>();

        setTitle("Dietary Log App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter food item:");
        foodItemInput = new JTextField(20);
        
        JButton logButton = new JButton("Log Food Item");
        logButton.setPreferredSize(new Dimension(150, 50)); // Set button size

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					logFoodItem();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(foodItemInput, BorderLayout.CENTER);
        inputPanel.add(logButton, BorderLayout.SOUTH);

        JPanel displayPanel = new JPanel(new BorderLayout());
        foodItemDisplay = new JTextArea(10, 20);
        foodItemDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(foodItemDisplay);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        updateFoodItemDisplay();
        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    private void logFoodItem() throws SQLException {
        String foodItem = foodItemInput.getText().trim();
        DietaryLogMain dietaryLog = new DietaryLogMain();
        
    	    PreparedStatement ps = connection.prepareStatement("INSERT INTO logs (user_id, log) VALUES (?, ?)");
    	    ps.setString(1, username);
    	    ps.setString(2, foodItem);
    		ps.executeUpdate();

    		updateFoodItemDisplay();
    }

    private void updateFoodItemDisplay() throws SQLException {
System.out.println("hello world");
    	    PreparedStatement ps = connection.prepareStatement("SELECT log FROM logs WHERE user_id = ?");
    	    ps.setString(1, username);
    	    ResultSet rs = ps.executeQuery();
    	    StringBuilder reviews = new StringBuilder();
    	    while (rs.next()) {
    	        // Retrieve each column value separately
    	        String log = rs.getString("log");
    	        
    	        
    	        // Append the retrieved values to the StringBuilder
    	        reviews.append(log).append("\n");
    	    }
    	    System.out.println(username);
    	    rs.close();
    	    ps.close();
    	
        foodItemDisplay.setText(reviews.toString());
    }

    private void showLoggedFoodItems() {
        StringBuilder sb = new StringBuilder();
        sb.append("Logged Food Items:\n");
        for (String foodItem : foodItems) {
            sb.append(foodItem).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Logged Food Items", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the GUI after showing the logged food items
    }

   
}