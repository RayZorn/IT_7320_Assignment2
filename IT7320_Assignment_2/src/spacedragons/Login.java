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

public class Login extends JFrame {

	JFrame frame;

	private JPanel contentPane;
	private JTextField nameText;
	private JPasswordField passwordText;

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String dbUrl = "jdbc:mysql://localhost:3306/spacedragons";
	static final String uname = "root";
	static final String password = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 23, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Start of Header
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(new Color(101, 255, 03));
		lblLogo.setFont(new Font("Candara", Font.BOLD, 14));
		Image img = new ImageIcon(this.getClass().getResource("/ZorpLogoSmall.png")).getImage();
		lblLogo.setIcon(new ImageIcon(img));	
		lblLogo.setBounds(168, 27, 84, 64);
		contentPane.add(lblLogo);

		JLabel lblEnterYourDetails = new JLabel("Enter Details to ");
		lblEnterYourDetails.setForeground(new Color(101, 255, 03));
		lblEnterYourDetails.setFont(new Font("Candara", Font.BOLD, 18));
		lblEnterYourDetails.setBounds(10, 89, 131, 23);
		contentPane.add(lblEnterYourDetails);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(101, 255, 03));
		lblLogin.setFont(new Font("Candara", Font.BOLD, 48));
		lblLogin.setBounds(134, 106, 143, 49);
		contentPane.add(lblLogin);

		JLabel lblToShortlist = new JLabel("to ZorbFetch");
		lblToShortlist.setForeground(new Color(101, 255, 03));
		lblToShortlist.setFont(new Font("Candara", Font.BOLD, 18));
		lblToShortlist.setBounds(275, 146, 113, 23);
		contentPane.add(lblToShortlist);
		// End of Header

		// Email Address
		JLabel lblGalacticDesignationname = new JLabel("Galactic Designation (name):");
		lblGalacticDesignationname.setHorizontalAlignment(SwingConstants.CENTER);
		lblGalacticDesignationname.setForeground(new Color(101, 255, 03));
		lblGalacticDesignationname.setFont(new Font("Candara", Font.BOLD, 16));
		lblGalacticDesignationname.setBounds(116, 213, 197, 28);
		contentPane.add(lblGalacticDesignationname);

		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(155, 238, 121, 20);
		contentPane.add(nameText);

		// Password
		JLabel lblPassword = new JLabel("Zorbword:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(101, 255, 03));
		lblPassword.setFont(new Font("Candara", Font.BOLD, 16));
		lblPassword.setBounds(155, 281, 121, 28);
		contentPane.add(lblPassword);

		passwordText = new JPasswordField();
		passwordText.setBounds(155, 305, 121, 20);
		contentPane.add(passwordText);

		// Go Button
		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String uName = nameText.getText();
				String pswd = passwordText.getText();
				
				try {
					connect = DriverManager.getConnection(dbUrl, uname, password);
					
					String sql = "select * from citizen where name = '" + uName + "'";
					
					preparedStatement = connect.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet.next()) {
						
						if (pswd.equals(resultSet.getString("password"))) {
							JOptionPane.showMessageDialog(frame, "Login Success");
							int citizenId = resultSet.getInt("citizenId");
							Parking parking = new Parking(citizenId);
							parking.setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Incorrect Password", "Oops!",
									JOptionPane.ERROR_MESSAGE);					
					}
					else
						JOptionPane.showMessageDialog(null, "No such Galactic Zorb designation in database!", "Oops!",
								JOptionPane.ERROR_MESSAGE);
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});

		btnGo.setFont(new Font("Candara", Font.BOLD, 18));
		btnGo.setForeground(new Color(127, 23, 105));
		btnGo.setBackground(new Color(101, 255, 03));
		btnGo.setBounds(157, 346, 119, 49);
		contentPane.add(btnGo);

		// Back Button
		JButton button = new JButton("<<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home = new Home();
				home.setVisible(true);
				dispose();
			}
		});
		button.setForeground(new Color(127, 23, 105));
		button.setBackground(new Color(101, 255, 03));
		button.setBounds(10, 414, 64, 23);
		contentPane.add(button);

		// Exit Button
		JButton button_1 = new JButton("X");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setForeground(new Color(127, 23, 105));
		button_1.setBackground(new Color(101, 255, 3));
		button_1.setBounds(378, 10, 47, 23);
		contentPane.add(button_1);

	}
}
