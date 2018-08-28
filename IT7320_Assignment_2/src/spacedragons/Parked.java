package spacedragons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Parked extends JFrame {

	private JPanel contentPane;
	
	private int citizenId;
	private int dragonId;
	private int invoiceId;
	
	private String dragonName = "empty";
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String dbUrl = "jdbc:mysql://localhost:3306/spacedragons";
	static final String uname = "root";
	static final String password = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parked frame = new Parked(1,1,1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param passedInvoiceId2 
	 * @param passedDragonId 
	 * @param passedCitizenId 
	 */
	public Parked(int passedCitizenId, int passedDragonId, int passedInvoiceId) {
		this.citizenId = passedCitizenId;
		this.dragonId = passedDragonId;
		this.invoiceId = passedInvoiceId;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Retrieve Dragon");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Retrieve retrieve = new Retrieve(invoiceId);
				retrieve.setVisible(true);
				dispose();
			}
		});
		button.setForeground(new Color(127, 23, 105));
		button.setFont(new Font("Candara", Font.BOLD, 18));
		button.setBackground(new Color(101, 255, 3));
		button.setBounds(125, 397, 163, 28);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Go to Dashboard");
		button_1.setForeground(new Color(127, 23, 105));
		button_1.setFont(new Font("Candara", Font.BOLD, 18));
		button_1.setBackground(new Color(101, 255, 3));
		button_1.setBounds(104, 358, 199, 28);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("<html><body><center>Zorp accepts your wise gratitude</center></html></body>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(101, 255, 3));
		label.setFont(new Font("Candara", Font.BOLD, 16));
		label.setBounds(79, 282, 245, 42);
		contentPane.add(label);
		
		//get dragon's name: 
		try {
			connect = DriverManager.getConnection(dbUrl, uname, password);
			
			String sql = "select * from dragon where dragonId = '" + dragonId + "'";
			
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				dragonName = resultSet.getString("name");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel label_1 = new JLabel("<html><body><center>Your dragon: " + dragonName + " has been sent to the Demiplane of Parking!</center></html></body>");
		label_1.setForeground(new Color(101, 255, 3));
		label_1.setFont(new Font("Candara", Font.BOLD, 16));
		label_1.setBounds(79, 67, 245, 75);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Logo goes here");
		label_2.setForeground(new Color(101, 255, 3));
		label_2.setFont(new Font("Candara", Font.BOLD, 14));
		label_2.setBounds(153, 12, 104, 23);
		contentPane.add(label_2);
		
		JButton button_2 = new JButton("X");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setForeground(new Color(127, 23, 105));
		button_2.setBackground(new Color(101, 255, 3));
		button_2.setBounds(365, 11, 47, 23);
		contentPane.add(button_2);
	}

}
