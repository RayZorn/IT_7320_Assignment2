package spacedragons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class Retrieve extends JFrame {

	private JPanel contentPane;
	private JTextField txtTotalcost;
	private JTextField txtDateparked;
	private JTextField txtDateretrieved;
	
	private int invoiceId;
	private int dragonId;
	private int citizenId;
	private int currentBalance;
	private int totalCosts;
	private String events;
	
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
					Retrieve frame = new Retrieve(1, 1, "none");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param invoiceId 
	 */
	public Retrieve(int invoiceId, int totalCosts, String events) {
		this.invoiceId = invoiceId;
		this.totalCosts = totalCosts;
		this.events = events;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(101, 255, 3));
		label.setFont(new Font("Candara", Font.BOLD, 14));
		Image img = new ImageIcon(this.getClass().getResource("/ZorpLogoSmall.png")).getImage();
		label.setIcon(new ImageIcon(img));	
		label.setBounds(10, 11, 84, 64);
		contentPane.add(label);
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setForeground(new Color(127, 23, 105));
		button.setBackground(new Color(101, 255, 3));
		button.setBounds(378, 11, 47, 23);
		contentPane.add(button);
		
		JLabel lbldragonCheckout = new JLabel("<html><body><center>Dragon Checkout</center></html></body>");
		lbldragonCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lbldragonCheckout.setForeground(new Color(101, 255, 3));
		lbldragonCheckout.setFont(new Font("Candara", Font.BOLD, 16));
		lbldragonCheckout.setBounds(92, 46, 245, 23);
		contentPane.add(lbldragonCheckout);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancel.setText("It is too late for that now");
				btnCancel.setBounds(104, 398, 233, 28);
			}
		});
		btnCancel.setForeground(new Color(127, 23, 105));
		btnCancel.setFont(new Font("Candara", Font.BOLD, 18));
		btnCancel.setBackground(new Color(101, 255, 3));
		btnCancel.setBounds(155, 395, 119, 28);
		contentPane.add(btnCancel);
		
		JButton btnAcceptThePrice = new JButton("Accept the Price");
		btnAcceptThePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//If user pays:
				
				//Set the date paid on invoice
				try {
					connect = DriverManager.getConnection(dbUrl, uname, password);

					String sql = "UPDATE invoice SET datePaid = '" + now.toString() + "' WHERE invoiceId = '" + invoiceId + "'";
					//even if it says parts of this are depricated and slashes through them, you still need to leave them there.

					preparedStatement = connect.prepareStatement(sql);
					preparedStatement.executeUpdate();
					
					//Get the dragon Id
					try {
						connect = DriverManager.getConnection(dbUrl, uname, password);
						
						sql = "select * from invoice where invoiceId = '" + invoiceId + "'";
						
						preparedStatement = connect.prepareStatement(sql);
						resultSet = preparedStatement.executeQuery();
						
						if (resultSet.next()) {
							dragonId = resultSet.getInt("dragonId");				
						}
						
						//Set the dragon as unparked.
						try {
							connect = DriverManager.getConnection(dbUrl, uname, password);

							sql = "Update dragon SET parked = '" + 0 + "' WHERE `dragonId` = '" + dragonId + "'";
							//even if it says parts of this are depricated and slashes through them, you still need to leave them there.

							preparedStatement = connect.prepareStatement(sql);
							preparedStatement.executeUpdate();
							
							//Get the citizen Id
							try {
								connect = DriverManager.getConnection(dbUrl, uname, password);
								
								sql = "select * from invoice where invoiceId = '" + invoiceId + "'";
								
								preparedStatement = connect.prepareStatement(sql);
								resultSet = preparedStatement.executeQuery();
								
								if (resultSet.next()) {
									citizenId = resultSet.getInt("citizenId");				
								}
								
								//Get the citizen current balance
								try {
									connect = DriverManager.getConnection(dbUrl, uname, password);
									
									sql = "select * from citizen where citizenId = '" + citizenId + "'";
									
									preparedStatement = connect.prepareStatement(sql);
									resultSet = preparedStatement.executeQuery();
									
									if (resultSet.next()) {
										currentBalance = resultSet.getInt("zorpbucks");				
									}
									
									//update the citizen's balance
									try {
										connect = DriverManager.getConnection(dbUrl, uname, password);

										int newbalance = currentBalance - totalCosts;
										
										sql = "UPDATE citizen SET zorpbucks = '" + newbalance + "' WHERE citizenId = '" + citizenId + "'";
										//even if it says parts of this are depricated and slashes through them, you still need to leave them there.

										preparedStatement = connect.prepareStatement(sql);
										preparedStatement.executeUpdate();
										
										JOptionPane.showMessageDialog(contentPane, "Payment Success. Zorp thanks you for your loyalty");
										Parking parking = new Parking(citizenId);
										parking.setVisible(true);
										dispose();

									} catch (SQLException e) {
										JOptionPane.showMessageDialog(contentPane, "Failed to update balance");
										e.printStackTrace();
									}
								} catch (SQLException e) {
									JOptionPane.showMessageDialog(contentPane, "Failed to get Current Balance");
									e.printStackTrace();
								}
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(contentPane, "Failed to get citizen Id");
								e.printStackTrace();
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(contentPane, "Failed to set dragon as unparked");
							e.printStackTrace();
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(contentPane, "Failed to get dragon Id");
						e.printStackTrace();
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(contentPane, "Failed to set date paid");
					e.printStackTrace();
				}
				
			}
		});
		btnAcceptThePrice.setForeground(new Color(127, 23, 105));
		btnAcceptThePrice.setFont(new Font("Candara", Font.BOLD, 18));
		btnAcceptThePrice.setBackground(new Color(101, 255, 3));
		btnAcceptThePrice.setBounds(135, 338, 163, 28);
		contentPane.add(btnAcceptThePrice);
		
		JLabel lblTotalPrice = new JLabel("Total Cost:");
		lblTotalPrice.setForeground(new Color(101, 255, 3));
		lblTotalPrice.setFont(new Font("Candara", Font.BOLD, 14));
		lblTotalPrice.setBounds(107, 93, 75, 23);
		contentPane.add(lblTotalPrice);
		
		txtTotalcost = new JTextField();
		txtTotalcost.setEditable(false);
		txtTotalcost.setText(Integer.toString(this.totalCosts));
		txtTotalcost.setBounds(230, 93, 86, 20);
		contentPane.add(txtTotalcost);
		txtTotalcost.setColumns(10);
		
		JLabel lblDateParked = new JLabel("Date Parked:");
		lblDateParked.setForeground(new Color(101, 255, 3));
		lblDateParked.setFont(new Font("Candara", Font.BOLD, 14));
		lblDateParked.setBounds(107, 270, 86, 23);
		contentPane.add(lblDateParked);
		
		txtDateparked = new JTextField();
		txtDateparked.setEditable(false);
		txtDateparked.setText("DateParked");
		txtDateparked.setBounds(230, 270, 86, 20);
		contentPane.add(txtDateparked);
		txtDateparked.setColumns(10);
		
		JLabel lblDateRetrieved = new JLabel("Date Retrieved:");
		lblDateRetrieved.setForeground(new Color(101, 255, 3));
		lblDateRetrieved.setFont(new Font("Candara", Font.BOLD, 14));
		lblDateRetrieved.setBounds(107, 304, 97, 23);
		contentPane.add(lblDateRetrieved);
		
		txtDateretrieved = new JTextField();
		txtDateretrieved.setEditable(false);
		txtDateretrieved.setText(now.toString());
		txtDateretrieved.setBounds(230, 304, 86, 20);
		contentPane.add(txtDateretrieved);
		txtDateretrieved.setColumns(10);
		
		JTextPane txtpnEvents = new JTextPane();
		txtpnEvents.setEditable(false);
		txtpnEvents.setText(this.events);
		txtpnEvents.setBounds(107, 127, 209, 132);
		contentPane.add(txtpnEvents);
	}
}
