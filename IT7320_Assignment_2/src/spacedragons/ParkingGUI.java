package spacedragons;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Random;
import java.util.Timer; 
import java.util.TimerTask;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ParkingGUI extends JFrame {

	private JPanel contentPane;
	
	private int citizenId;
	private int dragonId;
	private int invoiceId;

	private int balance;
	private int price;
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String dbUrl = "jdbc:mysql://localhost:3306/spacedragons";
	static final String uname = "root";
	static final String password = "";
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	
	ParkingTimer timer = new ParkingTimer();
	double secondsPassed = 0;	
	boolean running = true;
	int totalCosts = 0;
	JButton btnStop = new JButton("Stop Parking");
	int dragonFined; 
	JTextArea additionalChargesTextArea = new JTextArea();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingGUI frame = new ParkingGUI(1,1,1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	



	public ParkingGUI(int passedCitizenId, int passedDragonId, int passedInvoiceId) {
		this.citizenId = passedCitizenId;
		this.dragonId = passedDragonId;
		this.invoiceId = passedInvoiceId;
		
		//get citizen's balance: 
				try {
					connect = DriverManager.getConnection(dbUrl, uname, password);
					
					String sql = "select * from citizen where citizenId = '" + citizenId + "'";
					
					preparedStatement = connect.prepareStatement(sql);
					resultSet = preparedStatement.executeQuery();
					
					if (resultSet.next()) {
						balance = resultSet.getInt("zorpbucks");				
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 273);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 23, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane timeText = new JTextPane();
		timeText.setText("0");
		timeText.setBorder(BorderFactory.createLineBorder(Color.black));
		timeText.setBounds(9, 131, 115, 20);
		contentPane.add(timeText);
		
		JTextPane moneyOwed = new JTextPane();
		moneyOwed.setText("$0");
		moneyOwed.setBorder(BorderFactory.createLineBorder(Color.black));
		moneyOwed.setBounds(152, 131, 123, 20);
		contentPane.add(moneyOwed);
		
		JTextPane balanceTextPane = new JTextPane();
		balanceTextPane.setText("$ " + Integer.toString(balance));
		balanceTextPane.setBorder(BorderFactory.createLineBorder(Color.black));
		balanceTextPane.setBounds(152, 42, 123, 20);
		contentPane.add(balanceTextPane);
		
		JButton btnStart = new JButton("Start Parking");
		btnStart.addMouseListener(new MouseAdapter() 
		{
			int costsCounter = 0;
			
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{				
				btnStop.setVisible(true);
				//running = timer.start();
				
				Timer myTimer = new Timer("StopWatchTime");
				
				Random rand = new Random();
				
				
				
				TimerTask task = new TimerTask()
				{
					public void run() 
					{
						if(running == false)
						{
							//btnStart.setEnabled(true);
							myTimer.cancel();
							running = true;
						}
						else
						{
							if(costsCounter == 10) 
							{
								totalCosts = totalCosts + 13;
								
								moneyOwed.setText("$" + Integer.toString(totalCosts));
								
								costsCounter = 0;
							}
							
							dragonFined = rand.nextInt(10) + 1;
							
							if(dragonFined == 5) 
							{
								System.out.println("Opps1" + dragonFined / 10 );	
								
								additionalChargesTextArea.setText(additionalChargesTextArea.getText() + "\n\n" + "Opps1. +$100");
								
								totalCosts = totalCosts + 100;
							} 
								else if(dragonFined == 6) 
							{
								System.out.println("Opps2" + dragonFined / 10 );	
								
								additionalChargesTextArea.setText(additionalChargesTextArea.getText() + "\n\n" + "Opps2. +$200");
								
								totalCosts = totalCosts + 200;
							}						
							
							costsCounter++;
							
							//btnStart.setEnabled(false);
							btnStart.setVisible(false);
							//System.out.println(running);
							secondsPassed++;					
							timeText.setText(Double.toString(secondsPassed / 10));		
							System.out.println(secondsPassed / 10 );	
						}
					}
				};	
				
				myTimer.scheduleAtFixedRate(task, 100, 100);
			}
		});
		
		
		
		btnStop.setVisible(false);
		
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				//secondsPassed = 0;
				btnStart.setVisible(true);
				btnStop.setVisible(false);
				//btnStart.setEnabled(true);
				running = timer.stop();
			}
		});
		btnStop.setBounds(10, 203, 114, 23);
		contentPane.add(btnStop);
		btnStart.setBounds(10, 169, 114, 23);
		contentPane.add(btnStart);
		
		String[] petStrings = { "Task1", "Task2", "Task3", "Task4", "Task5" };


		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		additionalChargesTextArea.setText("Description:");
		
		additionalChargesTextArea.setBounds(285, 42, 164, 184);
		//contentPane.add(additionalChargesTextArea);
		additionalChargesTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		scroll.setBounds(302, 42, 147, 184);
		scroll.setViewportView(additionalChargesTextArea);
		contentPane.add(scroll);
		

		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setForeground(Color.GREEN);
		lblPayment.setBounds(152, 106, 97, 14);
		contentPane.add(lblPayment);
		
		JLabel lblTimer = new JLabel("Timer");
		lblTimer.setForeground(Color.GREEN);
		lblTimer.setBounds(10, 106, 46, 14);
		contentPane.add(lblTimer);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				running = false;
				Retrieve retrieve = new Retrieve(invoiceId,  totalCosts, additionalChargesTextArea.getText());
				retrieve.setVisible(true);
				dispose();
				/* Calin - Commented this out while setting button to go to retrieve.java
				secondsPassed = 0;
				
				balanceTextPane.setText("$" + Integer.toString(balance - totalCosts));
				
				moneyOwed.setText("$0");
				timeText.setText("0");		
				
				totalCosts = 0;
				*/
			}
		});
		btnPay.setBounds(152, 169, 123, 23);
		contentPane.add(btnPay);
		
		JButton btnDonateDragon = new JButton("Donate Dragon");
		btnDonateDragon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				running = false;
				
				//try {
				//	connect = DriverManager.getConnection(dbUrl, uname, password);
				//	String sql = "delete from dragon where dragonId = '" + dragonId + "'";					
				//	preparedStatement = connect.prepareStatement(sql);
				//	resultSet = preparedStatement.executeQuery();
					
				//} catch (SQLException e) {
				//	// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}					
				//Retrieve retrieve = new Retrieve(invoiceId,  totalCosts, additionalChargesTextArea.getText());
				//retrieve.setVisible(true);
				dispose();	
			}
		});
		btnDonateDragon.setBounds(152, 203, 123, 23);
		contentPane.add(btnDonateDragon);
		
		JLabel ZorbFetch = new JLabel("ZorbFetch");
		ZorbFetch.setHorizontalAlignment(SwingConstants.CENTER);
		ZorbFetch.setForeground(Color.GREEN);
		ZorbFetch.setBounds(0, 11, 93, 20);
		contentPane.add(ZorbFetch);
		
		JLabel lblAdditionalCharges = new JLabel("Additional Charges");
		lblAdditionalCharges.setForeground(Color.GREEN);
		lblAdditionalCharges.setBounds(302, 14, 123, 14);
		contentPane.add(lblAdditionalCharges);
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/ZorpLogoSmall.png")).getImage();
		
		logo.setIcon(new ImageIcon(img));	
		logo.setBounds(9, 31, 84, 64);
		contentPane.add(logo);
		

		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setForeground(Color.GREEN);
		lblBalance.setBounds(152, 14, 97, 14);
		contentPane.add(lblBalance);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
