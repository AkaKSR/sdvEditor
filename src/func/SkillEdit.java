package func;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SkillEdit {
	
	public SkillEdit() {
		
		System.out.println(" -- SkillEdit Constructor -- ");
		
	}
	
	public static Document skillInit(Document document) {
		
		Element getParent = (Element) document.getElementsByTagName("professions").item(0).getChildNodes();
		NodeList getInt = document.getElementsByTagName("professions").item(0).getChildNodes().item(0).getChildNodes();
		System.out.println("[Init] getParent: " + getParent);
		System.out.println("[Init] getInt: " + getInt.getLength());
		for (int i = 0; i < getInt.getLength(); i++) {
			Node a = getInt.item(i);
			getParent.removeChild(a.getParentNode());	
		}
		
		return document;
		
	}
	
	public static Document skillEdit(Document document, String skillInt) {
		
		Element getParent = (Element) document.getElementsByTagName("professions").item(0).getChildNodes();
		System.out.println("[Edit] getParent: " + getParent);
		
		Element intNode = document.createElement("int");
		intNode.appendChild(document.createTextNode(skillInt));
		getParent.appendChild(intNode);
		
		return document;
		
	}

}
