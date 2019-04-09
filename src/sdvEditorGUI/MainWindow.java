package sdvEditorGUI;

import java.awt.*;

public class MainWindow {

	Frame frame = new Frame("sdvEditor GUI");
	//Button button = new Button("Button");
	
	public void createFrame() {
		
		//frame.add(button);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//프레임 열기
		MainWindow frm = new MainWindow();
		frm.createFrame();
		
	}
	
}
