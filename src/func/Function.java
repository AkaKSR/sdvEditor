package func;

import java.io.IOException;

public class Function {
	
	public static void pause() {
		try {
			System.in.read();
		} catch (IOException e) {}
	}
	
	public static void cls() {
		
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
		
	}
	
}
