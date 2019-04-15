package sdvEditor;

import java.io.IOException;

public class Pause {
	
	public static void pause() {
		try {
			System.in.read();
		} catch (IOException e) {}
	}

}
