package sdvEditorGUI;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import func.Function;
import func.SkillEdit;

public class Func {
	
	public void fileFactory() {
		
	}
	
	public void init() throws ParserConfigurationException, SAXException, IOException {
		
		// sdvEditor Function load
		Function function = new Function();
		SkillEdit se = new SkillEdit();
		
		// DOM Factory �ʱ�ȭ
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		// XML ���� �Ľ�
		String savefile = "";
		Document document = documentBuilder.parse(savefile);
		document.setXmlStandalone(true);
		
		
		
	}

}
