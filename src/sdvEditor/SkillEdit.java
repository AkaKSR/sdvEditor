/*
 * FileName: SkillEdit.java
 * author: AkaKSR
 * version: 0.1_unstable
 */

package sdvEditor;

import java.util.Scanner;

import org.w3c.dom.Document;

import func.Function;
import res.StringMain;

public class SkillEdit {
	
	public void SkillEdit(Document document) {
		// Data Type init
		StringMain string = new StringMain();
		String func;
		Scanner sc = new Scanner(System.in);
		String skillInt;
		
		// skillEdit init
		string.skillEdit();
		func = sc.next();
		func = func.toUpperCase();
		switch (func) {
		case "Y":
			while (true) {
				System.out.println(" -- Skill Initializing Start -- ");
				Function.removeAllNode(document);
				string.skillMenu();
				System.out.println();
				System.out.println("Please input skill Code: ");
				skillInt = sc.next();
				if (skillInt.equals("exit")) {
					break;
				} else {
					document = Function.skillEdit(document, skillInt);
				}
			}
			break;
			
		case "N":
			break;
			
		default:
			System.out.println();
			break;
		}
		
		return;
	}
	
	public void SkillEditGUI(Document document, String skillInt) {
		document = Function.skillEdit(document, skillInt);
		return;
	}

}
