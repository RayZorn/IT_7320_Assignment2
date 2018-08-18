package spacedragons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Terms extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terms frame = new Terms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Terms and Conditions
	String terms = "<html><body><center>The following is a decleration  of acceptance"
			+ " by a citizen of the Empire of His holiness Lord Zorb. "
			+ "Users of this app agree to pay in full the anount displayed"
			+ " upon the retrieval of their dragon from the demi-plane of parking."
			+ " Costs are relative to the time spent and activities performed by your dragon."
			+ " If you do not retrieve your dragon within a reasonable time, it will be donated on your behalf"
			+ "to the majestic empire of His holiness Lord Zorb.</center><br><br>"
			+ "	<center>ALL HAIL ZORB.</center> </html></body>";

	public Terms() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 23, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Terms and Conditions");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(101, 255, 3));
		label.setFont(new Font("Candara", Font.BOLD, 42));
		label.setBounds(10, 39, 415, 49);
		contentPane.add(label);
		
		JLabel termsAndConditions = new JLabel(terms);
		termsAndConditions.setForeground(new Color(101, 255, 3));
		termsAndConditions.setBounds(57, 99, 320, 274);
		termsAndConditions.setFont(new Font("Candara", Font.BOLD, 16));
		contentPane.add(termsAndConditions);
		
		//Back Button
		JButton button = new JButton("<<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}
		});
		button.setForeground(new Color(127, 23, 105));
		button.setBackground(new Color(101, 255, 3));
		button.setBounds(183, 396, 64, 23);
		contentPane.add(button);
	}

}
