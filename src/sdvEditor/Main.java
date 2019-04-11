/**
 * FileName : ${sdvEditor}
 * Comment  : Stardew Valley Save Editor
 * version : 0.3
 * author  : AkaKSR
 * date    : ${2019.04.10}
 */

/*
 * Changelog
 * 0.1 = Project Initial
 * 0.2 = Buffer Exception bug fix / First Release
 * 0.2.1 = exe file Release
 * 0.3 = New function add(farmName, favoriteThing) / Support SaveGameInfo file / Android savefile bug fix
 */

package sdvEditor;

import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

/**
 * @author AkaKSR
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, SAXException, IOException, TransformerException{
		
		//System.out.println(" -- sdvEditor Start -- ");
		System.out.println("Stardew Valley Save Editor - sdvEditor");
		
		// DOM Factory 초기화
		//System.out.println(" -- DOM Factory init -- ");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();

		// Scanner 초기화
		System.out.print("Please type your savefile name : ");
		Scanner sc = new Scanner(System.in);
		String savefile = sc.next();
		System.out.println();
		//sc.close();
		
		// XML 문서 파싱
		//System.out.println(" -- File Load Start -- ");
		Document document = documentBuilder.parse(savefile);
		document.setXmlStandalone(true);
		
		// 현재 시간 구하기
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd_HHmmss");
		
		Date time = new Date();
		
		String time1 = format.format(time);
		
		
		/*// Save 백업
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(out);
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.transform(source, result);
		
		String xmlout = new String(out.toByteArray(), StandardCharsets.UTF_8);
		
		System.out.println(" -- File Backup Start -- ");
		
		FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + savefile + "_bak_" + time1, true);
		OutputStreamWriter osw = new OutputStreamWriter(output,"UTF-8");
		BufferedWriter outs = new BufferedWriter(osw);
		outs.write(xmlout);
		outs.close();
		
		System.out.println(" -- File Backup End -- ");
		*/
		
		// 파일 검증
		if (document.getDocumentElement().getNodeName() == "SaveGame") {
			System.out.println("Save Type : Main");
		} else if (document.getDocumentElement().getNodeName() == "Farmer") {
			System.out.println("Save Type : Info");
			System.out.println("-----------------------");
			System.out.println("This Program is main save only");
			System.out.println("Main save file Exam : User_uniqueID");
			System.out.println("Please check file");
			return;
			
		} else {
			System.out.println("This savefile is not Stardew Valley savefile.");
			System.out.println("Please check file");
			return;
		}
		
		NodeList nList = document.getElementsByTagName("player");
		System.out.println("------------------------");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;
				
				NodeGS ntv = new NodeGS();
				
				System.out.println();
				System.out.println("----------Menu----------");
				System.out.println("1. Edit Status");
				System.out.println("2. Check Status");
				System.out.println("3. Exit");
				System.out.println("------------------------");
				System.out.println();
				System.out.println("Please enter the Menu Number");
				System.out.print("Menu Num : ");
				int menu = sc.nextInt();
				System.out.println();
				if (menu == 1) {
					System.out.println("Please enter the function you want to change.");
					System.out.println("(name, farmName, favoriteThing, money, health, maxHealth, stamina, maxStamina, MaxItems, farmingLevel, miningLevel, combatLevel, foragingLevel, fishingLevel)");
					System.out.print("command: ");
					String func = sc.next();
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
				} else if (menu == 2) {
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
					return;
				} else if (menu == 3) {
					return;
				} else {
					System.out.println("Please try again.");
					System.out.println("Please Restart Program.");
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
		
		FileOutputStream output1 = new FileOutputStream(savefile + "_modified_" + time1, true);
		OutputStreamWriter osw1 = new OutputStreamWriter(output1,"UTF-8");
		BufferedWriter out2 = new BufferedWriter(osw1);
		out2.write(xmlout1);
		out2.close();
		
		System.out.println(" -- File Write End -- ");
	}

	
}
