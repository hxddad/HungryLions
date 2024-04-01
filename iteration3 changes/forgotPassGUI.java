import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class forgotPassGUI extends JFrame implements ActionListener {
    private JTextField newPasswordField, confirmPasswordField;

    public forgotPassGUI() {
        setTitle("Forgot Password");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Creating components
        JButton forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.addActionListener(this);

        // Adding components to the frame
        JPanel panel = new JPanel();
        panel.add(forgotPasswordButton);
        add(panel);
        
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Forgot Password")) {
            dispose(); // Close the main window
            
            // Creating the popup window
            JDialog popup = new JDialog(this, "Reset Password", true);
            popup.setLayout(new BorderLayout());
            popup.setSize(300, 200);
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);

            // Components for the popup window
            JPanel inputPanel = new JPanel(new GridLayout(3, 2));
            inputPanel.add(new JLabel("New Password:"));
            newPasswordField = new JTextField();
            inputPanel.add(newPasswordField);
            inputPanel.add(new JLabel("Confirm Password:"));
            confirmPasswordField = new JTextField();
            inputPanel.add(confirmPasswordField);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new SaveButtonListener(popup));
            inputPanel.add(saveButton);

            popup.add(inputPanel, BorderLayout.CENTER);
            popup.setVisible(true);
        }
    }

    private class SaveButtonListener implements ActionListener {
        private JDialog popup;

        public SaveButtonListener(JDialog popup) {
            this.popup = popup;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
                return;
            }
            
            // Here you can implement your logic to save the new password
            JOptionPane.showMessageDialog(null, "Password saved successfully.");
            newPasswordField.setText("");
            confirmPasswordField.setText("");
            popup.dispose(); // Close the popup window
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new forgotPassGUI().setVisible(true));
    }
}
