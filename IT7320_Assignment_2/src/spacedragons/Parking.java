package spacedragons;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Parking extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private int citizenId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parking frame = new Parking(1);
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
	public Parking(int passedCitizenId) {
		this.citizenId = passedCitizenId;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("logo goes here");
		label.setForeground(new Color(101, 255, 3));
		label.setFont(new Font("Candara", Font.BOLD, 14));
		label.setBounds(166, 11, 104, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Please park your dragon within the Transmogrificator");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(101, 255, 3));
		label_1.setFont(new Font("Candara", Font.BOLD, 14));
		label_1.setBounds(10, 50, 415, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("<html><body><center>When your dragon is within the Transmorgrification zone, press Transmorgrificate.</center></html></body>");
		label_2.setForeground(new Color(101, 255, 3));
		label_2.setFont(new Font("Candara", Font.BOLD, 16));
		label_2.setBounds(91, 84, 245, 63);
		contentPane.add(label_2);
		
		JButton button = new JButton("Transmorgrificate!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parked parked = new Parked();
				parked.setVisible(true);
				dispose();
			}
		});
		button.setForeground(new Color(127, 23, 105));
		button.setFont(new Font("Candara", Font.BOLD, 18));
		button.setBackground(new Color(101, 255, 3));
		button.setBounds(120, 284, 199, 28);
		contentPane.add(button);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("Ensure all arms, legs, wings, horns, tentacles, appendages, and other protuburances are within the Transmorgification zone before pressting transmorgrificate");
		textArea.setLineWrap(true);
		textArea.setForeground(new Color(102, 255, 0));
		textArea.setFont(new Font("Candara", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setBackground(new Color(102, 0, 102));
		textArea.setBounds(31, 374, 369, 49);
		contentPane.add(textArea);
		
		JButton button_1 = new JButton("X");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setForeground(new Color(127, 23, 105));
		button_1.setBackground(new Color(101, 255, 3));
		button_1.setBounds(366, 10, 47, 23);
		contentPane.add(button_1);
		
		JLabel lblSteedName = new JLabel("Steed Name:");
		lblSteedName.setForeground(new Color(101, 255, 3));
		lblSteedName.setFont(new Font("Candara", Font.BOLD, 16));
		lblSteedName.setBounds(71, 169, 86, 23);
		contentPane.add(lblSteedName);
		
		JLabel lblSpecies = new JLabel("Species:");
		lblSpecies.setForeground(new Color(101, 255, 3));
		lblSpecies.setFont(new Font("Candara", Font.BOLD, 16));
		lblSpecies.setBounds(71, 218, 86, 23);
		contentPane.add(lblSpecies);
		
		textField = new JTextField();
		textField.setBounds(166, 170, 210, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(131, 219, 245, 20);
		contentPane.add(textField_1);
	}
}
