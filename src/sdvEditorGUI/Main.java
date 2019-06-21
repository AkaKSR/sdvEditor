package sdvEditorGUI;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import func.Function;
import func.NodeGS;
import res.*;

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
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;

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
	private JButton btnHelp;
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
	public static Function function;
	public static Document document;
	public static NodeGS nodeGS;
	private JTextPane txtpnLog;
	private String loadPath;

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
		URL imageurl = getClass().getClassLoader().getResource("about_32x32.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(imageurl));
		setFont(new Font("Cousine", Font.BOLD, 12));
		setTitle("Stardew Valley Save Editor - sdvEditor GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 543);
		
		JFileChooser chooser;
		chooser = new JFileChooser();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setFont(new Font("Cousine", Font.BOLD, 12));
		btnOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				FileOpen fileOpen = new FileOpen();
				loadPath = fileOpen.filePath;
				System.out.println(loadPath);
				
				try {
					document = function.domInit().parse(loadPath);
					document.setXmlStandalone(true);
					NodeList nList = document.getElementsByTagName("player");
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							txtName.setText(nodeGS.nodegv("name", eElement));
							txtMoney.setText(nodeGS.nodegv("money", eElement));
							txtInv.setText(nodeGS.nodegv("maxItems", eElement));
							txtHealth.setText(nodeGS.nodegv("health", eElement));
							txtMaxHealth.setText(nodeGS.nodegv("maxHealth", eElement));
							txtStamina.setText(nodeGS.nodegv("stamina", eElement));
							txtMaxStamina.setText(nodeGS.nodegv("maxStamina", eElement));
							txtFarm.setText(nodeGS.nodegv("farmingLevel", eElement));
							txtMine.setText(nodeGS.nodegv("miningLevel", eElement));
							txtCombat.setText(nodeGS.nodegv("combatLevel", eElement));
							txtForaging.setText(nodeGS.nodegv("foragingLevel", eElement));
							txtFish.setText(nodeGS.nodegv("fishingLevel", eElement));
							txtpnLog.setText("File Load Complete.");
						}
						
					}
					
				} catch (SAXException | IOException | ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					txtpnLog.setText("Wrong File Load.");
				}
				
			}
		});
		btnOpen.setBounds(14, 12, 105, 27);
		contentPane.add(btnOpen);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Cousine", Font.BOLD, 12));
		lblName.setBounds(14, 66, 62, 18);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Cousine", Font.BOLD, 12));
		txtName.setBounds(14, 89, 116, 24);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Cousine", Font.BOLD, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// File Save
//				FileSave fileSave = new FileSave();
//				String savePath = fileSave.filePath;
				System.out.println(loadPath);
				
				try {
					document = function.domInit().parse(loadPath);
					document.setXmlStandalone(true);
					NodeList nList = document.getElementsByTagName("player");
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							nodeGS.nodesv("name", eElement, txtName.getText());
							nodeGS.nodesv("money", eElement, txtMoney.getText());
							nodeGS.nodesv("maxItems", eElement, txtInv.getText());
							nodeGS.nodesv("health", eElement, txtHealth.getText());
							nodeGS.nodesv("maxHealth", eElement, txtMaxHealth.getText());
							nodeGS.nodesv("stamina", eElement, txtStamina.getText());
							nodeGS.nodesv("maxStamina", eElement, txtMaxStamina.getText());
							nodeGS.nodesv("farmingLevel", eElement, txtFarm.getText());
							nodeGS.nodesv("miningLevel", eElement, txtMine.getText());
							nodeGS.nodesv("combatLevel", eElement, txtCombat.getText());
							nodeGS.nodesv("foragingLevel", eElement, txtForaging.getText());
							nodeGS.nodesv("fishingLevel", eElement, txtFish.getText());
							function.fileSave(document, loadPath);
							txtpnLog.setText("File Save Complete.");
						}
						
					}
					
				} catch (SAXException | IOException | ParserConfigurationException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					txtpnLog.setText("File Save Error.");
				}
				
			}
		});
		btnSave.setBounds(133, 12, 105, 27);
		contentPane.add(btnSave);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Cousine", Font.BOLD, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnExit.setBounds(609, 12, 105, 27);
		contentPane.add(btnExit);
		
		btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Cousine", Font.BOLD, 12));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				About.run();
				
			}
		});
		btnAbout.setBounds(490, 12, 105, 27);
		contentPane.add(btnAbout);
		
		lblMoney = new JLabel("Money");
		lblMoney.setFont(new Font("Cousine", Font.BOLD, 12));
		lblMoney.setBounds(133, 66, 62, 18);
		contentPane.add(lblMoney);
		
		txtMoney = new JTextField();
		txtMoney.setFont(new Font("Cousine", Font.BOLD, 12));
		txtMoney.setBounds(133, 89, 116, 24);
		contentPane.add(txtMoney);
		txtMoney.setColumns(10);
		
		lblHealth = new JLabel("Health");
		lblHealth.setFont(new Font("Cousine", Font.BOLD, 12));
		lblHealth.setBounds(14, 125, 62, 18);
		contentPane.add(lblHealth);
		
		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Help.run();
				
			}
		});
		btnHelp.setFont(new Font("Cousine", Font.BOLD, 12));
		btnHelp.setBounds(371, 12, 105, 27);
		contentPane.add(btnHelp);
		
		lblMaxHealth = new JLabel("Max Health");
		lblMaxHealth.setFont(new Font("Cousine", Font.BOLD, 12));
		lblMaxHealth.setBounds(133, 125, 105, 18);
		contentPane.add(lblMaxHealth);
		
		txtHealth = new JTextField();
		txtHealth.setFont(new Font("Cousine", Font.BOLD, 12));
		txtHealth.setBounds(14, 148, 116, 24);
		contentPane.add(txtHealth);
		txtHealth.setColumns(10);
		
		txtMaxHealth = new JTextField();
		txtMaxHealth.setFont(new Font("Cousine", Font.BOLD, 12));
		txtMaxHealth.setBounds(133, 148, 116, 24);
		contentPane.add(txtMaxHealth);
		txtMaxHealth.setColumns(10);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setFont(new Font("Cousine", Font.BOLD, 12));
		lblStamina.setBounds(252, 125, 62, 18);
		contentPane.add(lblStamina);
		
		txtStamina = new JTextField();
		txtStamina.setFont(new Font("Cousine", Font.BOLD, 12));
		txtStamina.setColumns(10);
		txtStamina.setBounds(252, 148, 116, 24);
		contentPane.add(txtStamina);
		
		JLabel lblMaxStamina = new JLabel("Max Stamina");
		lblMaxStamina.setFont(new Font("Cousine", Font.BOLD, 12));
		lblMaxStamina.setBounds(371, 125, 105, 18);
		contentPane.add(lblMaxStamina);
		
		txtMaxStamina = new JTextField();
		txtMaxStamina.setFont(new Font("Cousine", Font.BOLD, 12));
		txtMaxStamina.setColumns(10);
		txtMaxStamina.setBounds(371, 148, 116, 24);
		contentPane.add(txtMaxStamina);
		
		JLabel lblMaxInv = new JLabel("Max Inv.");
		lblMaxInv.setFont(new Font("Cousine", Font.BOLD, 12));
		lblMaxInv.setBounds(252, 66, 62, 18);
		contentPane.add(lblMaxInv);
		
		txtInv = new JTextField();
		txtInv.setFont(new Font("Cousine", Font.BOLD, 12));
		txtInv.setColumns(10);
		txtInv.setBounds(252, 89, 116, 24);
		contentPane.add(txtInv);
		
		JLabel lblFarmingLevel = new JLabel("Farming Level");
		lblFarmingLevel.setFont(new Font("Cousine", Font.BOLD, 12));
		lblFarmingLevel.setBounds(14, 184, 105, 18);
		contentPane.add(lblFarmingLevel);
		
		txtFarm = new JTextField();
		txtFarm.setFont(new Font("Cousine", Font.BOLD, 12));
		txtFarm.setColumns(10);
		txtFarm.setBounds(14, 207, 116, 24);
		contentPane.add(txtFarm);
		
		JLabel lblMiningLevel = new JLabel("Mining Level");
		lblMiningLevel.setFont(new Font("Cousine", Font.BOLD, 12));
		lblMiningLevel.setBounds(133, 184, 105, 18);
		contentPane.add(lblMiningLevel);
		
		txtMine = new JTextField();
		txtMine.setFont(new Font("Cousine", Font.BOLD, 12));
		txtMine.setColumns(10);
		txtMine.setBounds(133, 207, 116, 24);
		contentPane.add(txtMine);
		
		JLabel lblCombatLevel = new JLabel("Combat Level");
		lblCombatLevel.setFont(new Font("Cousine", Font.BOLD, 12));
		lblCombatLevel.setBounds(252, 184, 105, 18);
		contentPane.add(lblCombatLevel);
		
		txtCombat = new JTextField();
		txtCombat.setFont(new Font("Cousine", Font.BOLD, 12));
		txtCombat.setColumns(10);
		txtCombat.setBounds(252, 207, 116, 24);
		contentPane.add(txtCombat);
		
		JLabel lblForagingLevel = new JLabel("Foraging Level");
		lblForagingLevel.setFont(new Font("Cousine", Font.BOLD, 12));
		lblForagingLevel.setBounds(371, 184, 105, 18);
		contentPane.add(lblForagingLevel);
		
		txtForaging = new JTextField();
		txtForaging.setFont(new Font("Cousine", Font.BOLD, 12));
		txtForaging.setColumns(10);
		txtForaging.setBounds(371, 207, 116, 24);
		contentPane.add(txtForaging);
		
		JLabel lblFishingLevel = new JLabel("Fishing Level");
		lblFishingLevel.setFont(new Font("Cousine", Font.BOLD, 12));
		lblFishingLevel.setBounds(490, 184, 105, 18);
		contentPane.add(lblFishingLevel);
		
		txtFish = new JTextField();
		txtFish.setFont(new Font("Cousine", Font.BOLD, 12));
		txtFish.setColumns(10);
		txtFish.setBounds(490, 207, 116, 24);
		contentPane.add(txtFish);
		
		txtpnLog = new JTextPane();
		txtpnLog.setEnabled(false);
		txtpnLog.setEditable(false);
		txtpnLog.setFont(new Font("Cousine", Font.BOLD, 12));
		txtpnLog.setBounds(14, 335, 704, 149);
		txtpnLog.setText("sdvEditor GUI initialize Complete.");
		contentPane.add(txtpnLog);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtName.setText("");
				txtMoney.setText("");
				txtInv.setText("");
				txtHealth.setText("");
				txtMaxHealth.setText("");
				txtStamina.setText("");
				txtMaxStamina.setText("");
				txtFarm.setText("");
				txtMine.setText("");
				txtCombat.setText("");
				txtForaging.setText("");
				txtFish.setText("");
				txtpnLog.setText("Clear Complete.");
				
			}
		});
		clear.setFont(new Font("Cousine", Font.BOLD, 12));
		clear.setBounds(250, 12, 105, 27);
		contentPane.add(clear);
	}
}
