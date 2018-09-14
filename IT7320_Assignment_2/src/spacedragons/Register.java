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
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
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
	static final String password = "";

	private JTextField zorpbucks;
	private JPasswordField confirmPassword;
	private JPasswordField setPassword;
	private JTextField name;

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

		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(new Color(101, 255, 03));
		lblLogo.setFont(new Font("Candara", Font.BOLD, 14));
		Image img = new ImageIcon(this.getClass().getResource("/ZorpLogoSmall.png")).getImage();
		lblLogo.setIcon(new ImageIcon(img));	
		lblLogo.setBounds(18, 10, 84, 64);
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

		//Name label and Text
		JLabel lblName = new JLabel("Galactic Designation:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setForeground(new Color(101, 255, 03));
		lblName.setFont(new Font("Candara", Font.BOLD, 16));
		lblName.setBounds(58, 149, 152, 28);
		contentPane.add(lblName);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(214, 153, 152, 20);
		contentPane.add(name);

		// Zorpbucks Label and text
		JLabel lblZorpbucks = new JLabel("Zorpbucks deposit:");
		lblZorpbucks.setHorizontalAlignment(SwingConstants.LEFT);
		lblZorpbucks.setForeground(new Color(101, 255, 3));
		lblZorpbucks.setFont(new Font("Candara", Font.BOLD, 16));
		lblZorpbucks.setBounds(58, 201, 132, 28);
		contentPane.add(lblZorpbucks);

		zorpbucks = new JTextField();
		zorpbucks.setColumns(10);
		zorpbucks.setBounds(200, 205, 166, 20);
		contentPane.add(zorpbucks);

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
	
						String sql = "INSERT INTO citizen(name, password, zorpbucks) " + "VALUES('"
								+ name.getText() + "','" + confirmPassword.getText() + "','" + zorpbucks.getText() + "')";
						//even if it says parts of this are depricated and slashes through them, you still need to leave them there.
	
						preparedStatement = connect.prepareStatement(sql);
						preparedStatement.executeUpdate();
						JOptionPane.showMessageDialog(frame, "You are now registered with zorbFetch");
	
						name.setText("");
						setPassword.setText("");
						confirmPassword.setText("");
						zorpbucks.setText("");
	
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
		
		JLabel lblname = new JLabel("(name)");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setForeground(new Color(101, 255, 3));
		lblname.setFont(new Font("Candara", Font.BOLD, 11));
		lblname.setBounds(125, 172, 152, 15);
		contentPane.add(lblname);

	}
}
