package sdvEditorGUI;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import func.SkillEdit;

public class Function {
	
	public void fileFactory() {
		
	}
	
	public void init(String savefile) throws ParserConfigurationException, SAXException, IOException {
		
		// DOM Factory 초기화
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		// XML 문서 파싱
//		String savefile = "";
		Document document = documentBuilder.parse(savefile);
		document.setXmlStandalone(true);
		
		if (document.getDocumentElement().getNodeName() == "SaveGame") {
			System.out.println("Save Type : Main");
			
		} else {
			System.out.println("Save type : Unknown");
			return;
		}
		
		NodeList nList = document.getElementsByTagName("player");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;
				}
			}
		
	}
	
	
	public void checkFile(Document document) {
		if (document.getDocumentElement().getNodeName() == "SaveGame") {
			System.out.println("Save Type : Main");
			return;
		} else {
			System.out.println("Save type : Unknown");
			return;
		}
	}
	
	
}

