package sdvEditorGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.Font;

public class About {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
		About window = new About();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("About");
		URL imageurl = getClass().getClassLoader().getResource("about_18x18.png");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(imageurl));
		frame.setBounds(100, 100, 450, 300);
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
		btnConfirm.setBounds(165, 214, 105, 27);
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
		
		JLabel lblVersionBuild = new JLabel("Version 0.1, Build 1906 (Unstable)");
		lblVersionBuild.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblVersionBuild.setBounds(122, 175, 290, 27);
		frame.getContentPane().add(lblVersionBuild);
	}
}
