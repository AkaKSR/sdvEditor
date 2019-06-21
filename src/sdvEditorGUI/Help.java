package sdvEditorGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Help {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
		Help window = new Help();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Help");
		URL imageurl = getClass().getClassLoader().getResource("about_18x18.png");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(imageurl));
		frame.setBounds(100, 100, 450, 551);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.dispose();
				
			}
		});
		btnConfirm.setBounds(155, 463, 105, 27);
		frame.getContentPane().add(btnConfirm);
		
		JLabel lblAboutImage = new JLabel();
		lblAboutImage.setIcon(new ImageIcon(imageurl));
		
		lblAboutImage.setBounds(14, 12, 64, 71);
		frame.getContentPane().add(lblAboutImage);
		
		JLabel lblNewLabel = new JLabel("sdvEditor GUI");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 25));
		lblNewLabel.setBounds(155, 12, 201, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStardewValleySave = new JLabel("Stardew Valley Save Editor GUI");
		lblStardewValleySave.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblStardewValleySave.setBounds(128, 39, 264, 27);
		frame.getContentPane().add(lblStardewValleySave);
		
		JTextArea textPane = new JTextArea();
		textPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setEditable(false);
		textPane.setText("sdvEditor GUI Help\r\n1. Open Stardew Valley Save\r\n*Save file template*\r\n*PlayerName_UniqueID*\r\nex) AkaKSR_154449785\r\n2. Edit save file\r\n3. Save\r\n4. Enjoy (Profit?)\r\n\r\n-----------------------------------\r\n\r\nButton Description\r\nOpen: Load Savefile\r\nSave: Save Savefile\r\nClear: initialize sdvEditor\r\nHelp: sdvEditor GUI Help\r\nAbout: About\r\nExit: Program Exit");
		textPane.setLineWrap(true);
		textPane.setBounds(128, 78, 290, 352);
		frame.getContentPane().add(textPane);
	}
}
