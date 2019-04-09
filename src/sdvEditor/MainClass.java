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
			 * 파일 불러오기 시작
			 * sdvEditor에서는 메인 세이브 파일만 지원한다.
			 * 세이브파일명은 다음과 같이 이루어져있다.
			 * PlayerName_uniqueID
			 * 세이브 파일은 xml형식으로 이루어짐.
			 */
			System.out.println(" -- File Load Start -- ");
			 
			  File fXmlFile = new File(savefile);
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(fXmlFile);
			  doc.getDocumentElement().normalize();
			  
			  /*
			   * 세이브 파일 포멧 체크
			   * sdvEditor에서는 세이브 파일을 두가지로 파악한다.
			   * doc.getDocumentElement().getNodeName() 에서 받아들인 Node명을 기준으로 정상적인 세이브 파일인지 확인한다.
			   * Node명이 SaveGame일 경우 정상적인 메인세이브 파일로 파악하며  Node명이 Farmer이거나 그 이외일경우에는 안내문이 나온다.
			   * Node명이 Farmer인 파일은 SaveGameInfo 파일이다.
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
			         * 세이브 파일에서 해당 속성 불러오기
			         * 여기서 불러오는 속성은 다음과 같다.
			         * name = 플레이어 이름
			         * money = 보유중인 돈
			         * health = 체력
			         * maxHealth = 최대 체력
			         * stamina = 스테미너
			         * maxStamina = 최대 스테미너
			         * maxItems = 인벤토리 크기(인벤토리 크기는 12, 24, 36중 하나로 고정)
			         * farmingLevel = 농업 레벨
			         * miningLevel = 채광 레벨
			         * combatLevel = 전투 레벨
			         * foragingLevel = 채집 레벨
			         * fishingLevel = 낚시 레벨
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
			         * 불러온 세이브 파일의 내용 수정 부분
			         */
			        System.out.println(" -- xml child parsing -- ");
			        
			        
			        
			        System.out.println(" -- xml child parsing Complete --");
			        
			        /*
			         * 
			         */
			        System.out.println(" -- File Save Start -- ");
			        
			        
			        
			        System.out.println(" -- File Save Complete -- ");
			        /*
			         * sdvEditor 종료
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
