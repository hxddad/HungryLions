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
    private JButton viewReviewButton;
    private String selectedRestaurant;
	private JLabel welcomePrompt;
	private JTextArea portionValueArea;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel label;
	private JTextArea favoritesList;
	private JLabel favoritesLabel;
	private JButton btnAddToFavorites;
	private favourites favourites = new favourites();
	private JTextArea reviewArea = new JTextArea(2, 35);
	private JButton Challenges;
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
		setBounds(100, 100, 488, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		welcomePrompt = new JLabel("Welcome back, " + username + "!");
		welcomePrompt.setBounds(10, 11, 237, 14);
		contentPane.add(welcomePrompt);
		
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(183, 345, 164, 23); 
        contentPane.add(changePasswordButton);
        
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            ChangePasswordGUI changePasswordGUI = new ChangePasswordGUI(username);
                            //dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
		
		searchTextField = new JTextField();
		searchTextField.setBounds(125, 59, 122, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Restaurant:");
		lblNewLabel.setBounds(10, 62, 127, 14);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 445, 81);
		contentPane.add(scrollPane);
		
		resultArea = new JTextArea();
		scrollPane.setViewportView(resultArea);
		resultArea.setCaretPosition(0);
		
	      
		resultArea.setEditable(false);
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
		              portionValueArea.setLineWrap(true);
		              String text = "Payment Method: " + searchProgram.fetchPaymentMethod(selectedRestaurant) + "\nDietary Restrictions: " + searchProgram.fetchDietRestriction(selectedRestaurant) 
		              + "\nLocation: " + searchProgram.fetchLocation(selectedRestaurant);
		              reviewArea.setText(text);
		              

		              

		                
		            } catch (Exception ex) {
		                System.out.println("Error retrieving restaurant info: " + ex.getMessage());
		            }
		            if (!selectedRestaurant.isEmpty()) {
		                giveReviewButton.setEnabled(true);
		                viewReviewButton.setEnabled(true);
		                btnAddToFavorites.setEnabled(true);
		            }
		        }
		        
		    }
		});
		

		
		giveReviewButton = new JButton("Give Reviews");
		giveReviewButton.setEnabled(false);
		giveReviewButton.setBounds(10, 311, 163, 23);
		contentPane.add(giveReviewButton);
		viewReviewButton = new JButton("View Reviews");
		viewReviewButton.setEnabled(false);
		viewReviewButton.setBounds(10, 345, 163, 23);
		contentPane.add(viewReviewButton);
		
		btnAddToFavorites = new JButton("Add To Favorites");
		btnAddToFavorites.setEnabled(false);
		btnAddToFavorites.setBounds(183, 311, 164, 23);
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
		portionValueArea.setBounds(10, 179, 445, 40);
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
	       
	       cuisineComboBox.setBounds(315, 58, 140, 22);
	       contentPane.add(cuisineComboBox);
	       
	       label = new JLabel("Cuisine:");
	       label.setBounds(260, 62, 59, 14);
	       contentPane.add(label);
	       
	       
	       scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 404, 445, 80);
			contentPane.add(scrollPane_1);
			
			favoritesList = new JTextArea();
			scrollPane_1.setViewportView(favoritesList);
			
			
			
			favoritesList.setEditable(false);
			favoritesList.append(favourites.getFavorites(username));
	       
	       favoritesLabel = new JLabel("My Favorites:");
	       favoritesLabel.setBounds(10, 379, 373, 14);
	       contentPane.add(favoritesLabel);
	       
	       JButton btnNewButton = new JButton("Log Food");
	       btnNewButton.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
                DietaryLogGUI newFrame;
				try {
					newFrame = new DietaryLogGUI(username);
	                newFrame.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
	       	}
	       });
	       btnNewButton.setBounds(356, 311, 99, 23);
	       contentPane.add(btnNewButton);
	       
	       JLabel feedbackButton = new JLabel("Leave some feedback for us to improve!");
	       feedbackButton.setBounds(11, 495, 336, 14);
	       contentPane.add(feedbackButton);
	       
	       JButton reviewButton = new JButton("Leave Review");
	       reviewButton.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		FeedbackSystemGUI feedback = new FeedbackSystemGUI();
	       		feedback.setVisible(true);
	       	}
	       });
	       reviewButton.setBounds(10, 520, 194, 23);
	       contentPane.add(reviewButton);
	       
	       
	       reviewArea.setEditable(false);
	       reviewArea.setBounds(10, 230, 445, 70);
	       contentPane.add(reviewArea);
	       
	       Challenges = new JButton("Challenges");
	       Challenges.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       	}
	       });
	       Challenges.setBounds(357, 345, 98, 23);
	       contentPane.add(Challenges);
	        
	        giveReviewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                ReviewSystemGUI reviewSystemGUI = new ReviewSystemGUI(selectedRestaurant); 
	                reviewSystemGUI.setVisible(true);
	                
	            }
	        });
	        Challenges.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ChallengesGUI GUI = new ChallengesGUI();
	            	GUI.createAndShowGUI();
	                
	            }
	        });
	        viewReviewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ViewReviews viewReviews = null;
					try {
						viewReviews = new ViewReviews(selectedRestaurant);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	            	viewReviews.setVisible(true);
	                
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
