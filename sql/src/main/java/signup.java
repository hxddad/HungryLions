import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign up with HungryLions today!");
		lblNewLabel.setBounds(132, 27, 315, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setBounds(93, 75, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setBounds(93, 119, 82, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(165, 72, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JButton btnNewButton = new JButton("SIGN UP!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel success = new JLabel("Sign up successful! Head back to the login page.");
				success.setBounds(78, 210, 283, 14);
				success.setVisible(false);
				JLabel fail = new JLabel("Sorry, you already have an account with us!");
				fail.setBounds(78, 210, 283, 14);
				success.setBounds(78, 210, 283, 14);
				success.setVisible(false);
				fail.setVisible(false);
				contentPane.add(success);
				contentPane.add(fail);
					
				try {
					userdb db = new userdb();
					String username = textField.getText();
					char[] passwordChars = passwordField.getPassword();
					String password = new String(passwordChars);
					boolean add = db.addUser(username, password);
					if (add == false) {
		
						fail.setVisible(true);
						

					}
					else {
						success.setVisible(true);

					}
					btnNewButton.setEnabled(false);
				} catch (SQLException e1) {
					System.out.println("SQL not connected! Error!");
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(165, 157, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 116, 134, 20);
		contentPane.add(passwordField);
		

		
	}
}
