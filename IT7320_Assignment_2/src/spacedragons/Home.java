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

public class Home extends JFrame {
	
	
	

	JFrame frame;

	private JPanel contentPane;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 23, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image img = new ImageIcon(this.getClass().getResource("/ZorpLogoSmall.png")).getImage();
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(new Color(101, 255, 03));
		lblLogo.setFont(new Font("Candara", Font.BOLD, 14));
		lblLogo.setIcon(new ImageIcon(img));	
		lblLogo.setBounds(168, 27, 84, 64);
		contentPane.add(lblLogo);

		JLabel lblHome = new JLabel("Welcome to ZorbFetch");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setForeground(new Color(101, 255, 03));
		lblHome.setFont(new Font("Candara", Font.BOLD, 42));
		lblHome.setBounds(10, 134, 415, 49);
		contentPane.add(lblHome);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		loginButton.setForeground(new Color(127, 23, 105));
		loginButton.setFont(new Font("Candara", Font.BOLD, 18));
		loginButton.setBackground(new Color(101, 255, 3));
		loginButton.setBounds(151, 331, 119, 28);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}
		});
		registerButton.setForeground(new Color(127, 23, 105));
		registerButton.setFont(new Font("Candara", Font.BOLD, 18));
		registerButton.setBackground(new Color(101, 255, 3));
		registerButton.setBounds(151, 245, 119, 28);
		contentPane.add(registerButton);
		
		JButton xButton = new JButton("X");
		xButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		xButton.setForeground(new Color(127, 23, 105));
		xButton.setBackground(new Color(101, 255, 3));
		xButton.setBounds(378, 10, 47, 23);
		contentPane.add(xButton);
		

	}
}
