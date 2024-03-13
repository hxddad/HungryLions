import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FoodSearchGUI2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTextField;
	private JComboBox<String> cuisineComboBox;
    private JCheckBox halalCheckBox;
    private JCheckBox glutenFreeCheckBox;
    private JCheckBox veganCheckBox;
    private JCheckBox vegetarianCheckBox;
    
    private JTextArea resultArea;
    
    private FoodSearchProgram searchProgram;

    private PortionValueService portionValueService; 
    
    private JButton giveReviewButton;
    private String selectedRestaurant;
	private JLabel welcomePrompt;
	private JTextArea portionValueArea;
	private JScrollPane scrollPane;
	private JLabel label;
	private JTextArea favoritesList;
	private JLabel favoritesLabel;
	private JButton btnAddToFavorites;
	private favourites favourites = new favourites();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = "mommyLoveEating";
					FoodSearchGUI2 frame = new FoodSearchGUI2(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FoodSearchGUI2(String username) throws SQLException {
		searchProgram = new FoodSearchProgram();
        portionValueService = new PortionValueService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welcomePrompt = new JLabel("Welcome back, " + username + "!");
		welcomePrompt.setBounds(10, 11, 237, 14);
		contentPane.add(welcomePrompt);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(125, 59, 122, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Restaurant:");
		lblNewLabel.setBounds(10, 62, 127, 14);
		contentPane.add(lblNewLabel);
		
		resultArea = new JTextArea();
		resultArea.setCaretPosition(0);

		//resultArea.setBounds(10, 87, 414, 81);
		scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(10, 87, 414, 81);
		contentPane.add(scrollPane);
		

		
		giveReviewButton = new JButton("Give Reviews");
		giveReviewButton.setEnabled(false);
		giveReviewButton.setBounds(10, 224, 163, 23);
		contentPane.add(giveReviewButton);
		
		btnAddToFavorites = new JButton("Add To Favorites");
		btnAddToFavorites.setEnabled(false);
		btnAddToFavorites.setBounds(182, 224, 164, 23);
		contentPane.add(btnAddToFavorites);
		
		halalCheckBox = new JCheckBox("Halal");
		halalCheckBox.setBounds(10, 32, 69, 23);
		contentPane.add(halalCheckBox);
		
		glutenFreeCheckBox = new JCheckBox("Gluten-Free");
		glutenFreeCheckBox.setBounds(81, 32, 98, 23);
		contentPane.add(glutenFreeCheckBox);
		
		veganCheckBox = new JCheckBox("Vegan");
		veganCheckBox.setBounds(176, 32, 83, 23);
		contentPane.add(veganCheckBox);
		
		vegetarianCheckBox = new JCheckBox("Vegetarian");
		vegetarianCheckBox.setBounds(261, 32, 122, 23);
		contentPane.add(vegetarianCheckBox);
		
		portionValueArea = new JTextArea(2, 35);
		portionValueArea.setEditable(false);
		portionValueArea.setBounds(10, 173, 414, 40);
		contentPane.add(portionValueArea);
		
		
		
		 List<String> cuisines = searchProgram.fetchDistinctCuisines();
	     cuisines.add(0, "All Cuisine");
	     cuisineComboBox = new JComboBox<>(cuisines.toArray(new String[0]));
	     
	     cuisineComboBox.addActionListener(e -> searchAction());
	        halalCheckBox.addActionListener(e -> searchAction());
	        glutenFreeCheckBox.addActionListener(e -> searchAction());
	        veganCheckBox.addActionListener(e -> searchAction());
	        vegetarianCheckBox.addActionListener(e -> searchAction());
	        searchTextField.getDocument().addDocumentListener((SimpleDocumentListener) e -> searchAction());
	        
	      
	       resultArea.setEditable(false);
	       
	       cuisineComboBox.setBounds(315, 58, 109, 22);
	       contentPane.add(cuisineComboBox);
	       
	       label = new JLabel("Cuisine:");
	       label.setBounds(260, 62, 59, 14);
	       contentPane.add(label);
	       
	       favoritesList = new JTextArea();
	       
	       
	       scrollPane = new JScrollPane(favoritesList);
			scrollPane.setBounds(10, 287, 414, 48);
			contentPane.add(scrollPane);
			
	       
	       
	       
	       favoritesList.setEditable(false);
	       
	       favoritesLabel = new JLabel("My Favorites:");
	       favoritesLabel.setBounds(10, 258, 373, 14);
	       contentPane.add(favoritesLabel);
	       favoritesList.append(favourites.getFavorites(username));
	        resultArea.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                super.mouseClicked(e);
	                if (e.getClickCount() == 1) {
	                    int index = resultArea.viewToModel(e.getPoint());
	                    try {
	                        int start = resultArea.getLineStartOffset(resultArea.getLineOfOffset(index));
	                        int end = resultArea.getLineEndOffset(resultArea.getLineOfOffset(index));
	                        selectedRestaurant = resultArea.getDocument().getText(start, end - start).trim();
	                        String priceRange = portionValueService.getPriceRangeByName(selectedRestaurant);
	                        String portionValue = portionValueService.interpretPriceRangeAndPortion(priceRange);
	                      portionValueArea.setText(selectedRestaurant + ": " + portionValue);
	                        
	                    } catch (Exception ex) {
	                        System.out.println("Error retrieving restaurant info: " + ex.getMessage());
	                    }
	                    if (!selectedRestaurant.isEmpty()) {
	                        giveReviewButton.setEnabled(true);
	                        btnAddToFavorites.setEnabled(true);
	                    }
	                }
	                
	            }
	        });
	        
	        giveReviewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                ReviewSystemGUI reviewSystemGUI = new ReviewSystemGUI(selectedRestaurant); 
	                reviewSystemGUI.setVisible(true);
	                
	            }
	        });
	        btnAddToFavorites.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						System.out.println(favourites.addFavorites(selectedRestaurant, username));
						favoritesList.setText("");
						favoritesList.append(favourites.getFavorites(username));
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
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
}
