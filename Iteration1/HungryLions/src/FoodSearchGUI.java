package SearchFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FoodSearchGUI extends JFrame {
    private List<FoodSearchProgramMain> foodSpots;

    private JComboBox<String> cuisineComboBox;
    private JCheckBox halalCheckBox;
    private JCheckBox glutenFreeCheckBox;
    private JCheckBox veganCheckBox;
    private JCheckBox vegetarianCheckBox;
    private JTextArea resultArea;

    public FoodSearchGUI() {
        foodSpots = createSampleFoodSpots();

        setTitle("Food Search Program");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new GridLayout(5, 2));
        searchPanel.add(new JLabel("Desired Cuisine:"));
        cuisineComboBox = new JComboBox<>(new String[]{"Italian", "Southeast Asian", "North American", "Greek", "Japanese", "Mexican", "Mediterranean", "Korean", "Global Cuisines"});
        searchPanel.add(cuisineComboBox);
        searchPanel.add(new JLabel("Halal:"));
        halalCheckBox = new JCheckBox();
        searchPanel.add(halalCheckBox);
        searchPanel.add(new JLabel("Gluten Free:"));
        glutenFreeCheckBox = new JCheckBox();
        searchPanel.add(glutenFreeCheckBox);
        searchPanel.add(new JLabel("Vegan:"));
        veganCheckBox = new JCheckBox();
        searchPanel.add(veganCheckBox);
        searchPanel.add(new JLabel("Vegetarian:"));
        vegetarianCheckBox = new JCheckBox();
        searchPanel.add(vegetarianCheckBox);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performSearch() {
        String desiredCuisine = (String) cuisineComboBox.getSelectedItem();
        boolean isHalal = halalCheckBox.isSelected();
        boolean isGlutenFree = glutenFreeCheckBox.isSelected();
        boolean isVegan = veganCheckBox.isSelected();
        boolean isVegetarian = vegetarianCheckBox.isSelected();

        List<FoodSearchProgramMain> filteredFoodSpots = FoodSearchProgram.searchFoodSpots(foodSpots, desiredCuisine, isHalal, isGlutenFree, isVegan, isVegetarian);

        resultArea.setText("");
        if (filteredFoodSpots.isEmpty()) {
            resultArea.append("No matching food spots found.");
        } else {
            for (FoodSearchProgramMain foodSpot : filteredFoodSpots) {
                resultArea.append("Name: " + foodSpot.name + ", Cuisine: " + foodSpot.cuisineType + "\n");
            }
        }
    }

    private List<FoodSearchProgramMain> createSampleFoodSpots() {
        List<FoodSearchProgramMain> foodSpots = new ArrayList<>();
        foodSpots.add(new FoodSearchProgramMain("Marcello's", "Italian", false, false, false, true));
        foodSpots.add(new FoodSearchProgramMain("Thai Express", "Southeast Asian", true, true, false, true));
        foodSpots.add(new FoodSearchProgramMain("Burger Priest", "North American", false, false, false, false));
        foodSpots.add(new FoodSearchProgramMain("Mr. Greek", "Greek", true, true, true, true));
        foodSpots.add(new FoodSearchProgramMain("Sushi Haven", "Japanese", false, true, false, true));
        foodSpots.add(new FoodSearchProgramMain("Taco Fiesta", "Mexican", true, false, false, true));
        foodSpots.add(new FoodSearchProgramMain("Mediterranean Delight", "Mediterranean", true, true, true, false));
        foodSpots.add(new FoodSearchProgramMain("Woojoo Bunsik", "Korean", false, true, true, false));
        foodSpots.add(new FoodSearchProgramMain("Sofra", "Global Cuisines", false, true, false, false));
        return foodSpots;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FoodSearchGUI().setVisible(true);
            }
        });
    }
}