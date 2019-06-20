/**
 * @FileName : SkillEdit.java
 * @Comment  : sdvEditor SkillEdit method
 * @version : 0.2
 * @author  : AkaKSR
 * @date    : ${2019.06.12}
 */

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
	
	public static Document skillEdit(Document document, String skillInt) {
		
		Element getParent = (Element) document.getElementsByTagName("professions").item(0).getChildNodes();
		System.out.println("Skill add complete");
		
		
		Element intNode = document.createElement("int");
		intNode.appendChild(document.createTextNode(skillInt));
		getParent.appendChild(intNode);
		
		return document;
		
	}

}
