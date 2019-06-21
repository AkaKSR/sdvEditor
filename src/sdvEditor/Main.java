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
import func.SkillEdit;
import res.StringMain;
import func.Function;

/**
 * @author AkaKSR
 *
 */
public class Main {

	public static void main(String[] args) throws SAXException, IOException, InterruptedException, ParserConfigurationException, TransformerException {
		
		// etc initialize
		Function function = new Function();
		StringMain string = new StringMain();
		SkillEdit se = new SkillEdit();
		
		//System.out.println(" -- sdvEditor Start -- ");
		string.titleName();
		
		// Scanner initialize
		string.inputSave();
		Scanner sc = new Scanner(System.in);
		String savefile = sc.next();
		String skillInt;
		System.out.println();
		//sc.close();
		
		Document document = function.domInit().parse(savefile);
		document.setXmlStandalone(true);
		
		// savefile check
		function.saveCheck(document);
		
		NodeList nList = document.getElementsByTagName("player");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;
				NodeGS ntv = new NodeGS();
				
				// Clear screen and output menu.mainMenu()
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
					
					if (func.equals("skill")) {
						
						string.skillEdit();
						func = sc.next();
						func = func.toUpperCase();
						
						if (func.equals("Y")) {
							
							// Choose "Y"
							System.out.println(" -- Skill Initializing Start -- ");
							string.skillMenu();
							System.out.println();
							System.out.println("Please input skill Code:");
							skillInt = sc.next();
							document = se.skillEdit(document, skillInt);
							
							while (true) {
								
								System.out.println("repeat? (Y/N) ");
								func = sc.next();
								func = func.toUpperCase();
								
							if (func.equals("Y")) {
								
								System.out.println(" -- Skill Edit Start -- ");
								string.skillMenu();
								System.out.println();
								System.out.print("Please input skill Code: ");
								skillInt = sc.next();
								document = se.skillEdit(document, skillInt);
								
							} else if (func.equals("N")) {
								
								break;
								
							}
							
							}
							
						} else if (func.equals("N")) {
							
							// Choose "N"
							System.out.print("Edit " + func + " : ");
							String nsv = sc.next();
							System.out.println();
							System.out.println("before" + func + " : " + ntv.nodegv(func, eElement));
							System.out.println("after" + func + " : " + ntv.nodesv(func, eElement, nsv));
							InfoSave is = new InfoSave();
							is.infoSave(func, nsv);
							System.out.println("Press Enter key...");
							function.pause();
							
						}
						
					} else {
						
						System.out.print("Edit " + func + " : ");
						String nsv = sc.next();
						System.out.println();
						System.out.println("before" + func + " : " + ntv.nodegv(func, eElement));
						System.out.println("after" + func + " : " + ntv.nodesv(func, eElement, nsv));
						InfoSave is = new InfoSave();
						is.infoSave(func, nsv);
						System.out.println("Press Enter key...");
						function.pause();
						
					}
					
					
				} else if (menuint == 2) {
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
		
		// XML file save
		function.fileSave(document, savefile);
		
	}
		
}
