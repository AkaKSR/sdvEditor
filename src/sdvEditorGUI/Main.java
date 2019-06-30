/**
 * FileName	: sdvEditor GUI
 * Comment	: Stardew Valley Save Editor GUI
 * version	: 0.11
 * author	: AkaKSR
 * date		: 2019.06.26
 * build	: 1906_01 Stable
 */

package sdvEditorGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import func.Function;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JButton btnSave;
	private JButton btnExit;
	private JButton btnAbout;
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
	private JTextPane txtpnLog;
	private String loadPath;
	private JLabel lblSkill;
	private JLabel lblFarming;
	private JLabel lblFishing;
	private JLabel lblForaging;
	private JLabel lblMining;
	private JLabel lblCombat;
	
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
		setBounds(100, 100, 750, 583);
		
		JFileChooser chooser;
		chooser = new JFileChooser();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JCheckBox[] chkBox = new JCheckBox[30];
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
						Node lNode = document.getElementsByTagName("professions").item(0);
						int nodeLength = lNode.getChildNodes().getLength();
						System.out.println(nodeLength);
						
						for (int i = 0; i < nodeLength; i++) {
							String nodeStringValue = lNode.getChildNodes().item(i).getTextContent();
							int nodeValue = Integer.parseInt(nodeStringValue);
							chkBox[nodeValue].setSelected(true);
						}
						
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							txtName.setText(function.nodegv("name", eElement));
							txtMoney.setText(function.nodegv("money", eElement));
							txtInv.setText(function.nodegv("maxItems", eElement));
							txtHealth.setText(function.nodegv("health", eElement));
							txtMaxHealth.setText(function.nodegv("maxHealth", eElement));
							txtStamina.setText(function.nodegv("stamina", eElement));
							txtMaxStamina.setText(function.nodegv("maxStamina", eElement));
							txtFarm.setText(function.nodegv("farmingLevel", eElement));
							txtMine.setText(function.nodegv("miningLevel", eElement));
							txtCombat.setText(function.nodegv("combatLevel", eElement));
							txtForaging.setText(function.nodegv("foragingLevel", eElement));
							txtFish.setText(function.nodegv("fishingLevel", eElement));
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
				System.out.println(loadPath);
				
				try {
					document = function.domInit().parse(loadPath);
					document.setXmlStandalone(true);
					NodeList nList = document.getElementsByTagName("player");
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							function.nodesv("name", eElement, txtName.getText());
							function.nodesv("money", eElement, txtMoney.getText());
							function.nodesv("maxItems", eElement, txtInv.getText());
							function.nodesv("health", eElement, txtHealth.getText());
							function.nodesv("maxHealth", eElement, txtMaxHealth.getText());
							function.nodesv("stamina", eElement, txtStamina.getText());
							function.nodesv("maxStamina", eElement, txtMaxStamina.getText());
							function.nodesv("farmingLevel", eElement, txtFarm.getText());
							function.nodesv("miningLevel", eElement, txtMine.getText());
							function.nodesv("combatLevel", eElement, txtCombat.getText());
							function.nodesv("foragingLevel", eElement, txtForaging.getText());
							function.nodesv("fishingLevel", eElement, txtFish.getText());
							
							function.removeAllNode(document);
							for (int i = 0; i < chkBox.length; i++) {
								if (chkBox[i].isSelected() == true) {
									String skillInt = Integer.toString(i);
									Function.skillEdit(document, skillInt);
								}
							}
							
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
		txtpnLog.setForeground(Color.BLACK);
		txtpnLog.setEditable(false);
		txtpnLog.setFont(new Font("Cousine", Font.BOLD, 12));
		txtpnLog.setBounds(14, 497, 704, 27);
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
				
				for (int i = 0; i < chkBox.length; i++) {
					chkBox[i].setSelected(false);
				}
				
				txtpnLog.setText("Clear Complete.");
				
			}
		});
		clear.setFont(new Font("Cousine", Font.BOLD, 12));
		clear.setBounds(250, 12, 105, 27);
		contentPane.add(clear);
		
		JLabel lblDialog = new JLabel("Dialog");
		lblDialog.setFont(new Font("Cousine", Font.BOLD, 12));
		lblDialog.setBounds(14, 480, 62, 18);
		contentPane.add(lblDialog);
		
		// SkillEdit CheckBox
		lblSkill = new JLabel("Skill");
		lblSkill.setFont(new Font("Cousine", Font.BOLD, 12));
		lblSkill.setBounds(14, 243, 62, 18);
		contentPane.add(lblSkill);
		
		chkBox[0] = new JCheckBox("Rancher");
		chkBox[0].setBounds(14, 290, 105, 27);
		contentPane.add(chkBox[0]);
		
		chkBox[1] = new JCheckBox("Tiller");
		chkBox[1].setBounds(14, 321, 105, 27);
		contentPane.add(chkBox[1]);
		
		chkBox[2] = new JCheckBox("Coopmaster");
		chkBox[2].setBounds(14, 352, 116, 27);
		contentPane.add(chkBox[2]);
		
		chkBox[3] = new JCheckBox("Shepard");
		chkBox[3].setBounds(14, 383, 105, 27);
		contentPane.add(chkBox[3]);
		
		chkBox[4] = new JCheckBox("Artisan");
		chkBox[4].setBounds(14, 414, 105, 27);
		contentPane.add(chkBox[4]);
		
		chkBox[5] = new JCheckBox("Agricultist");
		chkBox[5].setBounds(14, 445, 105, 27);
		contentPane.add(chkBox[5]);
		
		lblFarming = new JLabel("Farming");
		lblFarming.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFarming.setBounds(14, 261, 62, 18);
		contentPane.add(lblFarming);
		
		lblFishing = new JLabel("Fishing");
		lblFishing.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFishing.setBounds(133, 261, 62, 18);
		contentPane.add(lblFishing);
		
		chkBox[6] = new JCheckBox("Fisher");
		chkBox[6].setBounds(133, 290, 105, 27);
		contentPane.add(chkBox[6]);
		
		chkBox[7] = new JCheckBox("Trapper");
		chkBox[7].setBounds(133, 321, 105, 27);
		contentPane.add(chkBox[7]);
		
		chkBox[8] = new JCheckBox("Angler");
		chkBox[8].setBounds(133, 352, 105, 27);
		contentPane.add(chkBox[8]);
		
		chkBox[9] = new JCheckBox("Pirate");
		chkBox[9].setBounds(133, 383, 105, 27);
		contentPane.add(chkBox[9]);
		
		chkBox[10] = new JCheckBox("Mariner");
		chkBox[10].setBounds(133, 414, 105, 27);
		contentPane.add(chkBox[10]);
		
		chkBox[11] = new JCheckBox("Luremaster");
		chkBox[11].setBounds(133, 445, 105, 27);
		contentPane.add(chkBox[11]);
		
		lblForaging = new JLabel("Foraging");
		lblForaging.setFont(new Font("Dialog", Font.BOLD, 12));
		lblForaging.setBounds(252, 261, 62, 18);
		contentPane.add(lblForaging);
		
		chkBox[12] = new JCheckBox("Forester");
		chkBox[12].setBounds(252, 290, 105, 27);
		contentPane.add(chkBox[12]);
		
		chkBox[13] = new JCheckBox("Forager");
		chkBox[13].setBounds(252, 321, 105, 27);
		contentPane.add(chkBox[13]);
		
		chkBox[14] = new JCheckBox("Lumberjack");
		chkBox[14].setBounds(252, 352, 105, 27);
		contentPane.add(chkBox[14]);
		
		chkBox[15] = new JCheckBox("Tapper");
		chkBox[15].setBounds(252, 383, 105, 27);
		contentPane.add(chkBox[15]);
		
		chkBox[16] = new JCheckBox("Botanist");
		chkBox[16].setBounds(252, 414, 105, 27);
		contentPane.add(chkBox[16]);
		
		chkBox[17] = new JCheckBox("Tracker");
		chkBox[17].setBounds(252, 445, 105, 27);
		contentPane.add(chkBox[17]);
		
		lblMining = new JLabel("Mining");
		lblMining.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMining.setBounds(371, 261, 62, 18);
		contentPane.add(lblMining);
		
		chkBox[18] = new JCheckBox("Miner");
		chkBox[18].setBounds(371, 290, 105, 27);
		contentPane.add(chkBox[18]);
		
		chkBox[19] = new JCheckBox("Geologist");
		chkBox[19].setBounds(371, 321, 105, 27);
		contentPane.add(chkBox[19]);
		
		chkBox[20] = new JCheckBox("Blacksmith");
		chkBox[20].setBounds(371, 352, 105, 27);
		contentPane.add(chkBox[20]);
		
		chkBox[21] = new JCheckBox("Prospector");
		chkBox[21].setBounds(371, 383, 105, 27);
		contentPane.add(chkBox[21]);
		
		chkBox[22] = new JCheckBox("Excavator");
		chkBox[22].setBounds(371, 414, 105, 27);
		contentPane.add(chkBox[22]);
		
		chkBox[23] = new JCheckBox("Gemologist");
		chkBox[23].setBounds(371, 445, 105, 27);
		contentPane.add(chkBox[23]);
		
		lblCombat = new JLabel("Combat");
		lblCombat.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCombat.setBounds(490, 261, 62, 18);
		contentPane.add(lblCombat);
		
		chkBox[24] = new JCheckBox("Fighter");
		chkBox[24].setBounds(490, 290, 105, 27);
		contentPane.add(chkBox[24]);
		
		chkBox[25] = new JCheckBox("Scout");
		chkBox[25].setBounds(490, 321, 105, 27);
		contentPane.add(chkBox[25]);
		
		chkBox[26] = new JCheckBox("Brute");
		chkBox[26].setBounds(490, 352, 105, 27);
		contentPane.add(chkBox[26]);
		
		chkBox[27] = new JCheckBox("Defender");
		chkBox[27].setBounds(490, 383, 105, 27);
		contentPane.add(chkBox[27]);
		
		chkBox[28] = new JCheckBox("Acrobat");
		chkBox[28].setBounds(490, 414, 105, 27);
		contentPane.add(chkBox[28]);
		
		chkBox[29] = new JCheckBox("Desperado");
		chkBox[29].setBounds(490, 445, 105, 27);
		contentPane.add(chkBox[29]);
	}
}
