import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton changePasswordButton;

    public ChangePasswordGUI() {
        frame = new JFrame("Change Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 2));

        frame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField();
        frame.add(newPasswordField);

        frame.add(new JLabel("Confirm New Password:"));
        confirmNewPasswordField = new JPasswordField();
        frame.add(confirmNewPasswordField);

        changePasswordButton = new JButton("Change Password");
        frame.add(changePasswordButton);
        
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
                String confirmNewPassword = new String(confirmNewPasswordField.getPassword());

                if (!newPassword.equals(confirmNewPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match.");
                    return;
                }

                boolean success = PasswordChanger.changePassword(username, newPassword);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Password changed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to change the password.");
                }
            }
        });

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChangePasswordGUI();
            }
        });
    }
}
