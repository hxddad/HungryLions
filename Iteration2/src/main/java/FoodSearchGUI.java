import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FoodSearchGUI extends JFrame {
    private JComboBox<String> cuisineComboBox;
    private JCheckBox halalCheckBox;
    private JCheckBox glutenFreeCheckBox;
    private JCheckBox veganCheckBox;
    private JCheckBox vegetarianCheckBox;
    private JTextField searchTextField; 
    private JTextArea resultArea;
    
    private FoodSearchProgram searchProgram;

    private JTextArea portionValueArea; 

    private PortionValueService portionValueService; 

    public FoodSearchGUI() {
        super("Food Search");
        searchProgram = new FoodSearchProgram();
        portionValueService = new PortionValueService(); 
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        List<String> cuisines = searchProgram.fetchDistinctCuisines();
        cuisines.add(0, "All Cuisine");
        cuisineComboBox = new JComboBox<>(cuisines.toArray(new String[0]));

        halalCheckBox = new JCheckBox("Halal");
        glutenFreeCheckBox = new JCheckBox("Gluten-Free");
        veganCheckBox = new JCheckBox("Vegan");
        vegetarianCheckBox = new JCheckBox("Vegetarian");
        searchTextField = new JTextField(20);
        
        resultArea = new JTextArea(10, 35);
        resultArea.setEditable(false);

        add(new JLabel("Cuisine:"));
        add(cuisineComboBox);
        add(halalCheckBox);
        add(glutenFreeCheckBox);
        add(veganCheckBox);
        add(vegetarianCheckBox);
        add(new JLabel("Search Restaurant:"));
        add(searchTextField);
        add(new JScrollPane(resultArea));

        cuisineComboBox.addActionListener(e -> searchAction());
        halalCheckBox.addActionListener(e -> searchAction());
        glutenFreeCheckBox.addActionListener(e -> searchAction());
        veganCheckBox.addActionListener(e -> searchAction());
        vegetarianCheckBox.addActionListener(e -> searchAction());
        searchTextField.getDocument().addDocumentListener((SimpleDocumentListener) e -> searchAction());
        
        portionValueArea = new JTextArea(2, 35);
        portionValueArea.setEditable(false);
        add(new JScrollPane(portionValueArea));

        resultArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    int index = resultArea.viewToModel(e.getPoint());
                    try {
                        int start = resultArea.getLineStartOffset(resultArea.getLineOfOffset(index));
                        int end = resultArea.getLineEndOffset(resultArea.getLineOfOffset(index));
                        String selectedRestaurant = resultArea.getDocument().getText(start, end - start).trim();
                        String priceRange = portionValueService.getPriceRangeByName(selectedRestaurant);
                        String portionValue = portionValueService.interpretPriceRangeAndPortion(priceRange);
                        portionValueArea.setText(selectedRestaurant + ": " + portionValue);
                    } catch (Exception ex) {
                        System.out.println("Error retrieving restaurant info: " + ex.getMessage());
                    }
                }
            }
        });

        pack();
        setLocationRelativeTo(null); 
    }

    private void searchAction() {
        String selectedCuisine = (String) cuisineComboBox.getSelectedItem();
        List<String> selectedCuisines = selectedCuisine.equals("All Cuisine") ? getAllCuisines() : List.of(selectedCuisine);
        boolean isHalal = halalCheckBox.isSelected();
        boolean isGlutenFree = glutenFreeCheckBox.isSelected();
        boolean isVegan = veganCheckBox.isSelected();
        boolean isVegetarian = vegetarianCheckBox.isSelected();
        String searchText = searchTextField.getText().trim();

        List<Restaurant> results = searchProgram.searchFoodSpots(selectedCuisines, isHalal, isGlutenFree, isVegan, isVegetarian, searchText);
        displayResults(results);
    }

    private List<String> getAllCuisines() {
        return searchProgram.fetchDistinctCuisines();
    }

    private void displayResults(List<Restaurant> results) {
        StringBuilder sb = new StringBuilder();
        for (Restaurant restaurant : results) {
            sb.append(restaurant.getName()).append("\n");
        }
        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FoodSearchGUI().setVisible(true);
        });
    }
}
