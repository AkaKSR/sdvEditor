/**
 * FileName : ${NodeGS}
 * Comment  : NodeGS
 * version : 0.1
 * author  : AkaKSR
 * date    : ${2019.04.10}
 */

package sdvEditor;

import java.util.Scanner;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author AkaKSR
 *
 */

public class NodeGS {
	
	public NodeGS() {
		//System.out.println(" -- NodeGS constructor -- ");
	}
	
	public static String nodegv(String gTag, Element eElement) {
		//System.out.println(" -- nodegv() Start -- ");
		NodeList nlList = eElement.getElementsByTagName(gTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		return nValue.getNodeValue();
	}
	
	public static String nodesv(String gTag, Element eElement, String sca) {
		//System.out.println(" -- nodesv() Start -- ");
		NodeList nlList = eElement.getElementsByTagName(gTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		
		Scanner ns = new Scanner(sca);
		String nss = ns.next();
		ns.close();
		nValue.setNodeValue(nss);
		return nValue.getNodeValue();
		
	}

}
