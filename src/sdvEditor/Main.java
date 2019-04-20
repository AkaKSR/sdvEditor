/**
 * FileName : ${sdvEditor}
 * Comment  : Stardew Valley Save Editor
 * version : 0.4.1
 * author  : AkaKSR
 * date    : ${2019.04.19}
 */

/*
 * Changelog
 * 0.1 = Project Initial
 * 0.2 = Buffer Exception bug fix / First Release
 * 0.2.1 = exe file Release
 * 0.3 = New function add(farmName, favoriteThing) / Support SaveGameInfo file / Android savefile bug fix
 * 0.4 = New function add(skill = professions)
 * 0.4.1 = skill function bug fix
 */

package sdvEditor;

import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import func.NodeGS;
import stringRes.StringMain;
import func.Function;

/**
 * @author AkaKSR
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, SAXException, IOException, TransformerException{
		
		// etc 초기화
		Function function = new Function();
		StringMain string = new StringMain();
		
		//System.out.println(" -- sdvEditor Start -- ");
		string.titleName();
		
		// DOM Factory 초기화
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();

		// Scanner 초기화
		string.inputSave();
		Scanner sc = new Scanner(System.in);
		String savefile = sc.next();
		System.out.println();
		//sc.close();
		
		// XML 문서 파싱
		Document document = documentBuilder.parse(savefile);
		document.setXmlStandalone(true);
		
		// 파일 검증
		if (document.getDocumentElement().getNodeName() == "SaveGame") {
			System.out.println("Save Type : Main");
		} else if (document.getDocumentElement().getNodeName() == "Farmer") {
			System.out.println("Save Type : Info");
			string.infoSave();
			return;
			
		} else {
			string.errorFile();
			return;
		}
		
		NodeList nList = document.getElementsByTagName("player");
		
		System.out.println("------------------------");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;
				NodeGS ntv = new NodeGS();
				
				
//				NodeList slList1 = eElement.getElementsByTagName("professions").item(0).getChildNodes();
//				Node sValue1 = (Node) slList1.item(0);
//				Element nodeDel = (Element) sValue1.getParentNode();
//				NodeList sValueChild = nodeDel.getChildNodes();
				
				//System.out.println("NodeName: " + sValue1.getParentNode());
				//System.out.println("NameParent: " + sValue1.getParentNode().getNodeName());
				//System.out.println("sValue1: " + sValue1);
				
				// 화면을 지운뒤 menu.mainMenu() 호출
				System.out.println();
				function.cls();
				string.mainMenu();
				int menuint = sc.nextInt();
				System.out.println();
				function.cls();

				if (menuint == 1) {
					string.statEdit();
					String func = sc.next();
					function.cls();
					
					// 스킬에딧 추가
					if ("skill".equals(func)) {
						NodeList slList1 = eElement.getElementsByTagName("professions").item(0).getChildNodes();
						Node sValue1 = (Node) slList1.item(0);
						Element nodeDel = (Element) sValue1.getParentNode();
						NodeList sValueChild = nodeDel.getChildNodes();
						
						String skillInt;
						System.out.println();
						string.skillEdit();
						String agree = sc.next();
						function.cls();
						
						if ("Y".equals(agree)) {
							
							// 현재 스킬 초기화
							for (int i = 0; i <= sValueChild.getLength(); ++i) {
								ntv.nodedel(document);
								
							} 
							
							string.skillMenu();
							
							String exit = "exit";
							
							while (true) {
								Element professions = document.createElement("int");
								System.out.println();
								System.out.print("Command: ");
								skillInt = sc.next();
								professions.appendChild(document.createTextNode(skillInt));
								nodeDel.appendChild(professions);
								System.out.print("More edit skill? (Y/N): ");
								String answer = sc.next();
								if (answer.equals("y")) {
									continue;
								} else if (answer.equals("Y")) {
									continue;
								} else if (answer.equals("n")) {
									break;
								} else if (answer.equals("N")) {
									break;
								} else break;
							}
							
						} else if (agree == "y") {
							
							// 현재 스킬 초기화
							for (int i = 0; i <= sValueChild.getLength(); ++i) {
								ntv.nodedel(document);
								
							} 
							
							string.skillMenu();
							
							String exit = "exit";
							
							while (true) {
								Element professions = document.createElement("int");
								System.out.println();
								System.out.println("Press ENTER without any input.");
								System.out.print("Command: ");
								skillInt = sc.next();
								professions.appendChild(document.createTextNode(skillInt));
								nodeDel.appendChild(professions);
								System.out.print("More edit skill? (Y/N): ");
								String answer = sc.next();
								if (answer.equals("y")) {
									continue;
								} else if (answer.equals("Y")) {
									continue;
								} else if (answer.equals("n")) {
									break;
								} else if (answer.equals("N")) {
									break;
								} else break;
							}
							
						} else {
							string.statEdit1();
							func = sc.next();
							function.cls();
							System.out.print("Edit " + func + " : ");
							String nsv = sc.next();
							System.out.println();
							System.out.println("before" + func + " : " + ntv.nodegv(func, eElement));
							System.out.println("after" + func + " : " + ntv.nodesv(func, eElement, nsv));
							InfoSave is = new InfoSave();
							is.infoSave(func, nsv);
							System.out.println("---------Main Save Status---------");
							System.out.println("name : " + ntv.nodegv("name", eElement));
							System.out.println("farmName : " + ntv.nodegv("farmName", eElement));
							System.out.println("favoriteThing : " + ntv.nodegv("favoriteThing", eElement));
							System.out.println("money : " + ntv.nodegv("money", eElement));
							System.out.println("health : " + ntv.nodegv("health", eElement));
							System.out.println("maxHealth : " + ntv.nodegv("maxHealth", eElement));
							System.out.println("stamina : " + ntv.nodegv("stamina", eElement));
							System.out.println("maxStamina : " + ntv.nodegv("maxStamina", eElement));
							System.out.println("maxItems : " + ntv.nodegv("maxItems", eElement));
							System.out.println("farmingLevel : " + ntv.nodegv("farmingLevel", eElement));
							System.out.println("miningLevel : " + ntv.nodegv("miningLevel", eElement));
							System.out.println("combatLevel : " + ntv.nodegv("combatLevel", eElement));
							System.out.println("foragingLevel : " + ntv.nodegv("foragingLevel", eElement));
							System.out.println("fishingLevel : " + ntv.nodegv("fishingLevel", eElement));
							System.out.println("------------------------");
							System.out.println();
							System.out.println("Press Enter key...");
							function.pause();
						}
						
						
					}
					
				} else if (menuint == 2) {
					System.out.println("---------Main Save Status---------");
					System.out.println("name : " + ntv.nodegv("name", eElement));
					System.out.println("farmName : " + ntv.nodegv("farmName", eElement));
					System.out.println("favoriteThing : " + ntv.nodegv("favoriteThing", eElement));
					System.out.println("money : " + ntv.nodegv("money", eElement));
					System.out.println("health : " + ntv.nodegv("health", eElement));
					System.out.println("maxHealth : " + ntv.nodegv("maxHealth", eElement));
					System.out.println("stamina : " + ntv.nodegv("stamina", eElement));
					System.out.println("maxStamina : " + ntv.nodegv("maxStamina", eElement));
					System.out.println("maxItems : " + ntv.nodegv("maxItems", eElement));
					System.out.println("farmingLevel : " + ntv.nodegv("farmingLevel", eElement));
					System.out.println("miningLevel : " + ntv.nodegv("miningLevel", eElement));
					System.out.println("combatLevel : " + ntv.nodegv("combatLevel", eElement));
					System.out.println("foragingLevel : " + ntv.nodegv("foragingLevel", eElement));
					System.out.println("fishingLevel : " + ntv.nodegv("fishingLevel", eElement));
					System.out.println("------------------------");
					System.out.println();
					System.out.println("Press Enter key...");
					function.pause();
					return;
					
					
				} else if (menuint == 3) {
					return;
				} else {
					System.out.println("Please try again.");
					System.out.println("Please Restart Program.");
					System.out.println();
					System.out.println("Press Enter key...");
					function.pause();
					return;
				}
				
				
				sc.close();
				
			}
			
		}
		
		// XML 파일 저장
		ByteArrayOutputStream out1 = new ByteArrayOutputStream();
		
		DOMSource source1 = new DOMSource(document);
		StreamResult result1 = new StreamResult(out1);
		
		TransformerFactory transFactory1 = TransformerFactory.newInstance();
		Transformer transformer1 = transFactory1.newTransformer();
		
		transformer1.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer1.transform(source1, result1);
		
		String xmlout1 = new String(out1.toByteArray(), StandardCharsets.UTF_8);
		
		System.out.println(" -- File Write Start -- ");
		
		FileOutputStream output1 = new FileOutputStream(savefile + "_modified", true);
		OutputStreamWriter osw1 = new OutputStreamWriter(output1,"UTF-8");
		BufferedWriter out2 = new BufferedWriter(osw1);
		out2.write(xmlout1);
		out2.close();
		
		System.out.println(" -- File Write End -- ");
	}

	
}
