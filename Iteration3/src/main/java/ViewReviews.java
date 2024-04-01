//abhiroop add comments
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ViewReviews extends JFrame {

    private String selectedRestaurant;
    private JTextArea reviewList;

    public ViewReviews(String selectedRestaurant) throws SQLException {
        this.selectedRestaurant = selectedRestaurant;
        initializeGUIComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350); // Set initial size
    }

    public ViewReviews() throws SQLException {
        this(null);
    }

    private void initializeGUIComponents() throws SQLException {
        setLayout(new BorderLayout()); // Change layout manager

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("Restaurant Name:"));
        topPanel.add(new JLabel(selectedRestaurant));
        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        reviewList = new JTextArea();
        reviewList.setEditable(false);
        scrollPane.setViewportView(reviewList);
        add(scrollPane, BorderLayout.CENTER);

        // Load reviews
        Reviews reviews = new Reviews();
        reviewList.setText(reviews.getReviews(selectedRestaurant));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ViewReviews();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}


