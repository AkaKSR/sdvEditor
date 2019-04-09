/**
 * FileName : ${sdvEditor}
 * Comment  : Stardew Valley
 * version : 0.1
 * author  : AkaKSR
 * date    : ${2019.04.09}
 */



package sdvEditor;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

/**
 * @author AkaKSR
 *
 */
public class MainClass {

	public static void main(String[] args) {
		
		System.out.println(" -- sdvEditor Start -- ");
		System.out.println("Stardew Valley Save Editor - sdvEditor");
		System.out.print("Please input your savefile : ");
		Scanner sc = new Scanner(System.in);
		String savefile = sc.next();
		sc.close();
		System.out.println();
		
		try {
			/*
			 * ���� �ҷ����� ����
			 * sdvEditor������ ���� ���̺� ���ϸ� �����Ѵ�.
			 * ���̺����ϸ��� ������ ���� �̷�����ִ�.
			 * PlayerName_uniqueID
			 * ���̺� ������ xml�������� �̷����.
			 */
			System.out.println(" -- File Load Start -- ");
			 
			  File fXmlFile = new File(savefile);
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(fXmlFile);
			  doc.getDocumentElement().normalize();
			  
			  /*
			   * ���̺� ���� ���� üũ
			   * sdvEditor������ ���̺� ������ �ΰ����� �ľ��Ѵ�.
			   * doc.getDocumentElement().getNodeName() ���� �޾Ƶ��� Node���� �������� �������� ���̺� �������� Ȯ���Ѵ�.
			   * Node���� SaveGame�� ��� �������� ���μ��̺� ���Ϸ� �ľ��ϸ�  Node���� Farmer�̰ų� �� �̿��ϰ�쿡�� �ȳ����� ���´�.
			   * Node���� Farmer�� ������ SaveGameInfo �����̴�.
			   */
			  if (doc.getDocumentElement().getNodeName() == "SaveGame") {
				System.out.println("Save Type : Main");
			} else if (doc.getDocumentElement().getNodeName() == "Farmer") {
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
			  
			   
			  
			  NodeList nList = doc.getElementsByTagName("player");
			  System.out.println("-----------------------");
			 
			  for (int temp = 0; temp < nList.getLength(); temp++) {
			 
			     Node nNode = nList.item(temp);
			     if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
			        Element eElement = (Element) nNode;
			        
			        /*
			         * ���̺� ���Ͽ��� �ش� �Ӽ� �ҷ�����
			         * ���⼭ �ҷ����� �Ӽ��� ������ ����.
			         * name = �÷��̾� �̸�
			         * money = �������� ��
			         * health = ü��
			         * maxHealth = �ִ� ü��
			         * stamina = ���׹̳�
			         * maxStamina = �ִ� ���׹̳�
			         * maxItems = �κ��丮 ũ��(�κ��丮 ũ��� 12, 24, 36�� �ϳ��� ����)
			         * farmingLevel = ��� ����
			         * miningLevel = ä�� ����
			         * combatLevel = ���� ����
			         * foragingLevel = ä�� ����
			         * fishingLevel = ���� ����
			         */
			        System.out.println("name : " + getTagValue("name", eElement));
			        System.out.println("money : " + getTagValue("money", eElement));
			        System.out.println("health : " + getTagValue("health", eElement));
			        System.out.println("maxHealth : " + getTagValue("maxHealth", eElement));
			        System.out.println("stamina : " + getTagValue("stamina", eElement));
			        System.out.println("maxStamina : " + getTagValue("maxStamina", eElement));
			        System.out.println("maxItems : " + getTagValue("maxItems", eElement));
			        System.out.println("farmingLevel : " + getTagValue("farmingLevel", eElement));
			        System.out.println("miningLevel : " + getTagValue("miningLevel", eElement));
			        System.out.println("combatLevel : " + getTagValue("combatLevel", eElement));
			        System.out.println("foragingLevel : " + getTagValue("foragingLevel", eElement));
			        System.out.println("fishingLevel : " + getTagValue("fishingLevel", eElement));
			        System.out.println(" -- File Load Complete -- ");
			        System.out.println();
			        
			        /*
			         * �ҷ��� ���̺� ������ ���� ���� �κ�
			         */
			        System.out.println(" -- xml child parsing -- ");
			        
			        
			        
			        System.out.println(" -- xml child parsing Complete --");
			        
			        /*
			         * 
			         */
			        System.out.println(" -- File Save Start -- ");
			        
			        
			        
			        System.out.println(" -- File Save Complete -- ");
			        /*
			         * sdvEditor ����
			         */
			        System.out.println(" -- sdvEditor End -- ");
			        return;
			        
			        
			        
			     }
			  }
			   } catch (Exception e) {
			  e.printStackTrace();
			   }
			  }
			 
			  private static String getTagValue(String sTag, Element eElement) {
			 NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
			 
			        Node nValue = (Node) nlList.item(0);
			 
			 return nValue.getNodeValue();
		
	}
	
}
