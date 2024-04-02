import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class ChangePasswordGUI extends JFrame{
    private JFrame frame;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton changePasswordButton;
    private String username; 

    public ChangePasswordGUI(String username) {
        this.username = username; 
        
        frame = new JFrame("Change Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 250); 
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 
        
        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Header
        JLabel headerLabel = new JLabel("Change Password", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20)); 
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(headerLabel);

        // Add some space
        frame.add(Box.createRigidArea(new Dimension(0, 10))); 

        // Display Username
        JLabel usernameLabel = new JLabel("Username: " + username, SwingConstants.CENTER);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(usernameLabel);

        // Add some space
        frame.add(Box.createRigidArea(new Dimension(0, 20))); 

     // Panel to contain the password fields and labels
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        frame.add(passwordPanel);

        // Inner panel for aligning the password fields
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2, 10, 10)); 
        passwordPanel.add(fieldsPanel);

        // New Password
        passwordPanel.add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField(10);
        passwordPanel.add(newPasswordField);

        // Confirm New Password
        passwordPanel.add(new JLabel("Confirm New Password:"));
        confirmNewPasswordField = new JPasswordField(10);
        passwordPanel.add(confirmNewPasswordField);

        // Change Password Button
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(changePasswordButton);
        
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = new String(newPasswordField.getPassword());
                String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

                if (!newPassword.equals(confirmNewPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match.");
                    return;
                }

                boolean success;
				try {
					success = PasswordChanger.changePassword(username, newPassword);
					 if (success) {
		                    JOptionPane.showMessageDialog(frame, "Password changed successfully.");
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Failed to change the password.");
		                }
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
            }
        });

        frame.add(Box.createRigidArea(new Dimension(0, 10)));

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChangePasswordGUI cpGui = new ChangePasswordGUI("james");
                cpGui.setVisible(true);
            }
        });
    }
}
