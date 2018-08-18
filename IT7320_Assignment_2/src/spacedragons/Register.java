package spacedragons;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Register extends JFrame {

	JFrame frame;

	private JPanel contentPane;

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;

	static final String dbUrl = "jdbc:mysql://localhost:3306/spacedragons";
	static final String uname = "root";
	static final String password = "password";

	private JTextField steedName;
	private JPasswordField confirmPassword;
	private JPasswordField setPassword;
	private JTextField nameLast_1;
	private JTextField nameFirst_1;
	private JTextField emailText;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 23, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("Logo goes here");
		lblLogo.setForeground(new Color(101, 255, 03));
		lblLogo.setFont(new Font("Candara", Font.BOLD, 14));
		lblLogo.setBounds(172, 11, 104, 23);
		contentPane.add(lblLogo);

		JLabel lblLogin = new JLabel("Register");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(101, 255, 03));
		lblLogin.setFont(new Font("Candara", Font.BOLD, 48));
		lblLogin.setBounds(112, 57, 196, 49);
		contentPane.add(lblLogin);

		JLabel lblToShortlist = new JLabel("for ZorbFetch");
		lblToShortlist.setForeground(new Color(101, 255, 03));
		lblToShortlist.setFont(new Font("Candara", Font.BOLD, 18));
		lblToShortlist.setBounds(144, 100, 113, 23);
		contentPane.add(lblToShortlist);

		// First Name label and Text
		JLabel nameFirst = new JLabel("Zorb Name:");
		nameFirst.setHorizontalAlignment(SwingConstants.CENTER);
		nameFirst.setForeground(new Color(101, 255, 03));
		nameFirst.setFont(new Font("Candara", Font.BOLD, 16));
		nameFirst.setBounds(38, 138, 121, 28);
		contentPane.add(nameFirst);

		nameFirst_1 = new JTextField();
		nameFirst_1.setColumns(10);
		nameFirst_1.setBounds(38, 158, 121, 20);
		contentPane.add(nameFirst_1);

		// Last Name Label and Text
		JLabel nameLast = new JLabel("Zorb Name:");
		nameLast.setHorizontalAlignment(SwingConstants.CENTER);
		nameLast.setForeground(new Color(101, 255, 03));
		nameLast.setFont(new Font("Candara", Font.BOLD, 16));
		nameLast.setBounds(38, 184, 121, 28);
		contentPane.add(nameLast);

		nameLast_1 = new JTextField();
		nameLast_1.setColumns(10);
		nameLast_1.setBounds(38, 205, 121, 20);
		contentPane.add(nameLast_1);

		// Email Address and text
		JLabel label_1 = new JLabel("Zorbmail Address:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(101, 255, 3));
		label_1.setFont(new Font("Candara", Font.BOLD, 16));
		label_1.setBounds(234, 134, 140, 28);
		contentPane.add(label_1);

		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(245, 158, 121, 20);
		contentPane.add(emailText);

		// Steed Name Label and text
		JLabel label = new JLabel("Steed Name:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(101, 255, 3));
		label.setFont(new Font("Candara", Font.BOLD, 16));
		label.setBounds(245, 184, 121, 28);
		contentPane.add(label);

		steedName = new JTextField();
		steedName.setColumns(10);
		steedName.setBounds(245, 205, 121, 20);
		contentPane.add(steedName);

		// Set Password
		JLabel label_2 = new JLabel("Set Zorbword:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(101, 255, 3));
		label_2.setFont(new Font("Candara", Font.BOLD, 16));
		label_2.setBounds(138, 236, 121, 28);
		contentPane.add(label_2);

		setPassword = new JPasswordField();
		setPassword.setBounds(136, 259, 121, 20);
		contentPane.add(setPassword);

		// Confirm Password
		JLabel label_3 = new JLabel("Confirm Zorbword:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(101, 255, 3));
		label_3.setFont(new Font("Candara", Font.BOLD, 16));
		label_3.setBounds(125, 291, 140, 28);
		contentPane.add(label_3);

		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(136, 312, 121, 20);
		contentPane.add(confirmPassword);
		
		//Terms Radio Button
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBounds(172, 414, 21, 23);
		rdbtnNewRadioButton.setForeground(new Color(101, 255, 3));
		rdbtnNewRadioButton.setBackground(new Color(127, 23, 105));
		contentPane.add(rdbtnNewRadioButton);

		// Go button
		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (rdbtnNewRadioButton.isSelected())
				{

					try {
						connect = DriverManager.getConnection(dbUrl, uname, password);
	
						String sql = "INSERT INTO user(nameFirst, nameLast, email,pswd,steedName) " + "VALUES('"
								+ nameFirst.getText() + "','" + nameLast.getText() + "','" + emailText.getText() + "','"
								+ confirmPassword.getText() + "','" + steedName.getText() + "')";
	
						preparedStatement = connect.prepareStatement(sql);
						preparedStatement.executeUpdate();
						JOptionPane.showMessageDialog(frame, "You are now registered with zorbFetch");
	
						nameFirst_1.setText("");
						nameLast_1.setText("");
						emailText.setText("");
						setPassword.setText("");
						confirmPassword.setText("");
						steedName.setText("");
	
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(frame, "You must agree to the terms and conditions");
			}
		});

		btnGo.setFont(new Font("Candara", Font.BOLD, 18));
		btnGo.setForeground(new Color(127, 23, 105));
		btnGo.setBackground(new Color(101, 255, 03));
		btnGo.setBounds(138, 343, 119, 41);
		contentPane.add(btnGo);
		
		//Back Button
		JButton back = new JButton("<<<");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
				dispose();
			}
		});
		back.setForeground(new Color(127, 23, 105));
		back.setBackground(new Color(101, 255, 03));
		back.setBounds(10, 414, 64, 23);
		contentPane.add(back);

		// Terms and Conditions
		JButton termsAndConditions = new JButton(
				"<HTML><BODY><span style='text-decoration:underline'>I agree to the Terms and Conditions</span></body></html>");
		termsAndConditions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Terms terms = new Terms();
				terms.setVisible(true);
				dispose();
			}
		});
		termsAndConditions.setHorizontalAlignment(SwingConstants.CENTER);
		termsAndConditions.setBackground(new Color(127, 23, 105));
		termsAndConditions.setForeground(new Color(101, 255, 3));
		termsAndConditions.setFont(new Font("Candara", Font.PLAIN, 12));
		termsAndConditions.setBounds(199, 409, 226, 28);
		contentPane.add(termsAndConditions);

		// Exit Button
		JButton exit = new JButton("X");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setForeground(new Color(127, 23, 105));
		exit.setBackground(new Color(101, 255, 3));
		exit.setBounds(378, 10, 47, 23);
		contentPane.add(exit);

	}
}
