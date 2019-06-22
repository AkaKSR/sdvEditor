package func;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Function {
	public static Scanner sc = new Scanner(System.in);
	public static Element eElement;
	
	// pause
	public static void pause() {
		try {
			System.in.read();
		} catch (IOException e) {}
	}
	
	// Screen clear(CLI Only)
	public static void cls() {
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
	}
	
	// DocumentBuilder initialize
	public static DocumentBuilder domInit() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		return documentBuilder;
	}
	
	// Save file check
	public static void saveCheck(Document document) {
		if (document.getDocumentElement().getNodeName() == "SaveGame") {
			System.out.println("Save Type : Main");
		} else {
			System.out.println("Save Type : Unknown");
			return;
		}
	}
	
	// File Save
	public static void fileSave(Document document, String savefile) throws TransformerException, IOException {
		
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
		OutputStreamWriter osw1 = new OutputStreamWriter(output1, "UTF-8");
		BufferedWriter out2 = new BufferedWriter(osw1);
		out2.write(xmlout1);
		out2.close();
		
		System.out.println(" -- File Write End -- ");
		
	}
	
	// Skill Edit
	public static Document skillEdit(Document document, String skillInt) {
		
		Element getParent = (Element) document.getElementsByTagName("professions").item(0).getChildNodes();
		System.out.println("Skill add complete");
		
		
		Element intNode = document.createElement("int");
		intNode.appendChild(document.createTextNode(skillInt));
		getParent.appendChild(intNode);
		
		return document;
	}
	
	public static String nodegv(String gTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(gTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		return nValue.getNodeValue();
	}
	
	public static String nodesv(String gTag, Element eElement, String sca) {
		NodeList nlList = eElement.getElementsByTagName(gTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		Scanner ns = new Scanner(sca);
		String nss = ns.next();
		ns.close();
		nValue.setNodeValue(nss);
		
		return nValue.getNodeValue();
	}
	
	/*
	 * 이 아래부터는 구현 진행중....
	 */
	
	// Repeat_test(CLI Only)
	public static void repeatNode(Document document, String skillInt) {
		while (true) {
			
			System.out.print("Repeat? (Y/N): ");
			String qustion = sc.next();
			qustion = qustion.toUpperCase();
			
			if (qustion.equals("Y")) {
				
				System.out.println("Answer: Y");
				
			} else if (qustion.equals("N")) {
				
				System.out.println("Answer: N");
				break;
				
			}
		}
	}
	
}
