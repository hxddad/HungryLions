import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainLogin frame = new mainLogin();
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
	public mainLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(180, 92, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel username = new JLabel("Username:");
		username.setBounds(107, 95, 70, 14);
		contentPane.add(username);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(107, 123, 70, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 120, 107, 20);
		contentPane.add(passwordField);
		
		lblNewLabel = new JLabel("Welcome to HungryLions!");
		lblNewLabel.setBounds(150, 35, 317, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(118, 161, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup newFrame = new signup();
				newFrame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(217, 161, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
