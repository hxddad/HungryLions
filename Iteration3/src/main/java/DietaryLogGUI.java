//package DietaryLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DietaryLogGUI extends JFrame {
    private List<String> foodItems;
    private JTextField foodItemInput;
    private JTextArea foodItemDisplay;

    public DietaryLogGUI() {
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
                logFoodItem();
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

        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    private void logFoodItem() {
        String foodItem = foodItemInput.getText().trim();
        DietaryLogMain dietaryLog = new DietaryLogMain();
        if (!foodItem.equalsIgnoreCase("done") && !foodItem.equalsIgnoreCase("finish")) {
            if (dietaryLog.logFoodItem(foodItem)) {
                foodItems.add(foodItem);
                updateFoodItemDisplay();
                foodItemInput.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid food item.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            showLoggedFoodItems();
        }
    }

    private void updateFoodItemDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String item : foodItems) {
            sb.append(item).append("\n");
        }
        foodItemDisplay.setText(sb.toString());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DietaryLogGUI gui = new DietaryLogGUI();
                gui.setVisible(true);
            }
        });
    }
}