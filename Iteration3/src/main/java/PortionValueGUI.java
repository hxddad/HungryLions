import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PortionValueGUI {
    private JFrame frame;
    private JComboBox<String> restaurantComboBox;
    private JTextArea resultArea;
    private PortionValueService service;

    public PortionValueGUI() {
        initialize();
        service = new PortionValueService();
        loadRestaurants();
    }

    private void initialize() {
        frame = new JFrame("Restaurant Finder");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        restaurantComboBox = new JComboBox<>();
        frame.add(restaurantComboBox);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        frame.add(resultArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRestaurant = (String) restaurantComboBox.getSelectedItem();
                if (selectedRestaurant != null) {
                    String[] parts = selectedRestaurant.split(" - ");
                    String priceRange = service.getPriceRangeByName(parts[0]); 
                    String interpretation = service.interpretPriceRangeAndPortion(priceRange);
                    resultArea.setText(interpretation);
                }
            }
        });
    }

    private void loadRestaurants() {
       
        List<String> restaurants = service.getRestaurantsWithLocation(); 
        for (String restaurant : restaurants) {
            restaurantComboBox.addItem(restaurant);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	PortionValueGUI app = new PortionValueGUI();
                    app.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
