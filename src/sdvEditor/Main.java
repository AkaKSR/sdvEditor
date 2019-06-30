/**
 * FileName : ${sdvEditor}
 * Comment  : Stardew Valley Save Editor
 * version : 0.5
 * author  : AkaKSR
 * date    : ${2019.05.04}
 */

package sdvEditor;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
						
						SkillEdit skillEdit = new SkillEdit();
						skillEdit.SkillEdit(document);
						
					} else {
						
						System.out.print("Edit " + func + " : ");
						String nsv = sc.next();
						System.out.println();
						System.out.println("before" + func + " : " + function.nodegv(func, eElement));
						System.out.println("after" + func + " : " + function.nodesv(func, eElement, nsv));
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
