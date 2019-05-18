package sdvEditorGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.TextField;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JButton btnSave;
	private JButton btnExit;
	private JButton btnAbout;
	private JButton btnBackup;
	private JLabel lblMoney;
	private JTextField txtMoney;
	private JLabel lblHealth;
	private JButton btnNewButton;
	private JLabel lblMaxHealth;
	private JTextField txtHealth;
	private JTextField txtMaxHealth;
	private JTextField txtStamina;
	private JTextField txtMaxStamina;
	private JTextField txtInv;
	private JTextField txtFarm;
	private JTextField txtMine;
	private JTextField txtCombat;
	private JTextField txtForaging;
	private JTextField txtFish;
	private JMenuBar menuBar;
	private JMenuItem mntmAbout;
	private JMenuItem mntmHelp;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Stardew Valley Save Editor - sdvEditor GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 543);
		
		JFileChooser chooser;
		chooser = new JFileChooser();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		mnFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Stardew Valley Save File", "*.*");
				chooser.setFileFilter(filter);
				
				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					
					JOptionPane.showMessageDialog(null, "File not Selected.", "Warning", JOptionPane.WARNING_MESSAGE);
					
					txtName.setText("성공");
					
					return;
					
				}
				
				String filePath = chooser.getSelectedFile().getPath();
				txtName.setText("케릭터 이름");
				
			}
		});
		
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnBackup = new JMenu("Backup");
		menuBar.add(mnBackup);
		
		JMenuItem mntmBackup = new JMenuItem("Backup");
		mnBackup.add(mntmBackup);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * 파일오픈 부분
				 * String filePath에 파일 경로 정보가 담김.
				 */
				FileOpen fileOpen = new FileOpen();
				String loadPath = fileOpen.filePath;
				
			}
		});
		btnOpen.setBounds(14, 12, 105, 27);
		contentPane.add(btnOpen);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(14, 66, 62, 18);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setText("NameValue");
		txtName.setBounds(14, 89, 116, 24);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// 세이브 버튼 이벤트 부분
				FileSave fileSave = new FileSave();
				String savePath = fileSave.filePath;
				
			}
		});
		btnSave.setBounds(133, 12, 105, 27);
		contentPane.add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnExit.setBounds(609, 12, 105, 27);
		contentPane.add(btnExit);
		
		btnAbout = new JButton("About");
		btnAbout.setBounds(490, 12, 105, 27);
		contentPane.add(btnAbout);
		
		btnBackup = new JButton("Backup");
		btnBackup.setBounds(252, 12, 105, 27);
		contentPane.add(btnBackup);
		
		lblMoney = new JLabel("Money");
		lblMoney.setBounds(133, 66, 62, 18);
		contentPane.add(lblMoney);
		
		txtMoney = new JTextField();
		txtMoney.setText("MoneyValue");
		txtMoney.setBounds(133, 89, 116, 24);
		contentPane.add(txtMoney);
		txtMoney.setColumns(10);
		
		lblHealth = new JLabel("Health");
		lblHealth.setBounds(14, 125, 62, 18);
		contentPane.add(lblHealth);
		
		btnNewButton = new JButton("Help");
		btnNewButton.setBounds(371, 12, 105, 27);
		contentPane.add(btnNewButton);
		
		lblMaxHealth = new JLabel("Max Health");
		lblMaxHealth.setBounds(133, 125, 105, 18);
		contentPane.add(lblMaxHealth);
		
		txtHealth = new JTextField();
		txtHealth.setText("HealthValue");
		txtHealth.setBounds(14, 148, 116, 24);
		contentPane.add(txtHealth);
		txtHealth.setColumns(10);
		
		txtMaxHealth = new JTextField();
		txtMaxHealth.setText("MaxHealthValue");
		txtMaxHealth.setBounds(133, 148, 116, 24);
		contentPane.add(txtMaxHealth);
		txtMaxHealth.setColumns(10);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(252, 125, 62, 18);
		contentPane.add(lblStamina);
		
		txtStamina = new JTextField();
		txtStamina.setText("StaminaValue");
		txtStamina.setColumns(10);
		txtStamina.setBounds(252, 148, 116, 24);
		contentPane.add(txtStamina);
		
		JLabel lblMaxStamina = new JLabel("Max Stamina");
		lblMaxStamina.setBounds(371, 125, 105, 18);
		contentPane.add(lblMaxStamina);
		
		txtMaxStamina = new JTextField();
		txtMaxStamina.setText("MSValue");
		txtMaxStamina.setColumns(10);
		txtMaxStamina.setBounds(371, 148, 116, 24);
		contentPane.add(txtMaxStamina);
		
		JLabel lblMaxInv = new JLabel("Max Inv.");
		lblMaxInv.setBounds(252, 66, 62, 18);
		contentPane.add(lblMaxInv);
		
		txtInv = new JTextField();
		txtInv.setText("Inv. Value");
		txtInv.setColumns(10);
		txtInv.setBounds(252, 89, 116, 24);
		contentPane.add(txtInv);
		
		JLabel lblFarmingLevel = new JLabel("Farming Level");
		lblFarmingLevel.setBounds(14, 184, 105, 18);
		contentPane.add(lblFarmingLevel);
		
		txtFarm = new JTextField();
		txtFarm.setText("FarmValue");
		txtFarm.setColumns(10);
		txtFarm.setBounds(14, 207, 116, 24);
		contentPane.add(txtFarm);
		
		JLabel lblMiningLevel = new JLabel("Mining Level");
		lblMiningLevel.setBounds(133, 184, 105, 18);
		contentPane.add(lblMiningLevel);
		
		txtMine = new JTextField();
		txtMine.setText("MineValue");
		txtMine.setColumns(10);
		txtMine.setBounds(133, 207, 116, 24);
		contentPane.add(txtMine);
		
		JLabel lblCombatLevel = new JLabel("Combat Level");
		lblCombatLevel.setBounds(252, 184, 105, 18);
		contentPane.add(lblCombatLevel);
		
		txtCombat = new JTextField();
		txtCombat.setText("CombatValue");
		txtCombat.setColumns(10);
		txtCombat.setBounds(252, 207, 116, 24);
		contentPane.add(txtCombat);
		
		JLabel lblForagingLevel = new JLabel("Foraging Level");
		lblForagingLevel.setBounds(371, 184, 105, 18);
		contentPane.add(lblForagingLevel);
		
		txtForaging = new JTextField();
		txtForaging.setText("ForagingValue");
		txtForaging.setColumns(10);
		txtForaging.setBounds(371, 207, 116, 24);
		contentPane.add(txtForaging);
		
		JLabel lblFishingLevel = new JLabel("Fishing Level");
		lblFishingLevel.setBounds(490, 184, 105, 18);
		contentPane.add(lblFishingLevel);
		
		txtFish = new JTextField();
		txtFish.setText("FishValue");
		txtFish.setColumns(10);
		txtFish.setBounds(490, 207, 116, 24);
		contentPane.add(txtFish);
	}
}
