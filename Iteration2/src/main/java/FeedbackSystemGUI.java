
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FeedbackSystemGUI extends JFrame {
  
	//made three text fields for the three types of feedback and then a button for submitting 
	//also an attribute of type feedbackSystem class to get all the checking systems working 
	private JTextField bugsField;
    private JTextField newFeaturesField;
    private JTextField criticismsField;
    private JButton submitButton;
    private FeedbackSystem feedbackSystem;

    //this is just the constructer with the set up method which does the main components
    public FeedbackSystemGUI() {
        feedbackSystem = new FeedbackSystem();
        setupGUI();
    }

    private void setupGUI() {
        //this makes the title and layout for the three text boxes 
    	setTitle("Submit Feedback");
        setLayout(new GridLayout(4, 2)); 

       
        add(new JLabel("Bugs or Errors:"));
        bugsField = new JTextField();
        add(bugsField);

        add(new JLabel("Feature Requests:"));
        newFeaturesField = new JTextField();
        add(newFeaturesField);

        add(new JLabel("Criticisms:"));
        criticismsField = new JTextField();
        add(criticismsField);
//this is the submit button for when you are done
        submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(this::submitFeedbackAction);
        add(submitButton);
//this finally closes everything after 
        setSize(485, 285); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void submitFeedbackAction(ActionEvent event) {
      //this simply decides whether to store or not store the data given depedning on whether the feedbackSystem components have all returned true
    	String bugs = bugsField.getText();
        String newFeatures = newFeaturesField.getText();
        String criticisms = criticismsField.getText();

        FeedbackSystem.Feedback feedback = new FeedbackSystem.Feedback(bugs, newFeatures, criticisms);

        try {
           //this will happen if everything is true which is it will store
        	feedbackSystem.submitFeedback(feedback);
            JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
           
        } catch (FeedbackSystem.SubmissionException e) {
           //it will not store if atleast on of them is false
        	JOptionPane.showMessageDialog(this, "Failed to submit feedback: " + "feedback not valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
    	//this is to just run the GUI as a whole
    	new FeedbackSystemGUI();
    }
}