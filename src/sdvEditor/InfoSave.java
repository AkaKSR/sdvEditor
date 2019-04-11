/**
 * FileName : ${InfoSave}
 * Comment  : Stardew Valley Save Editor(with SaveGameInfo)
 * version : 0.1
 * author  : AkaKSR
 * date    : ${2019.04.10}
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
public class InfoSave {
	
	public InfoSave() {
		
	}

	public static void infoSave(String func, String nsv) throws InterruptedException, ParserConfigurationException, SAXException, IOException, TransformerException {
		
		// DOM Factory 초기화
		//System.out.println(" -- DOM Factory init -- ");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		String infofile = "SaveGameInfo";
		
		// XML 문서 파싱
		//System.out.println(" -- File Load Start -- ");
		Document document = documentBuilder.parse(infofile);
		document.setXmlStandalone(true);
		
		// 현재 시간 구하기
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd_HHmmss");
		
		Date time = new Date();
		
		String time1 = format.format(time);
		
		NodeList nList = document.getElementsByTagName("Farmer");
		System.out.println("------------------------");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
				
				Element eElement = (Element) nNode;
				
				NodeGS ntv = new NodeGS();
				
					System.out.println();
					System.out.println("info_before" + func + " : " + ntv.nodegv(func, eElement));
					System.out.println("info_after" + func + " : " + ntv.nodesv(func, eElement, nsv));
					System.out.println();
					System.out.println("---------Infofile Status---------");
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
		
		FileOutputStream output1 = new FileOutputStream(infofile + "_modified_" + time1, true);
		OutputStreamWriter osw1 = new OutputStreamWriter(output1,"UTF-8");
		BufferedWriter out2 = new BufferedWriter(osw1);
		out2.write(xmlout1);
		out2.close();
		
		System.out.println(" -- File Write End -- ");
	}

	
}
