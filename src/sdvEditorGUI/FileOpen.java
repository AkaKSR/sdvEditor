package sdvEditorGUI;

import java.io.File;
import javax.swing.JFileChooser;

public class FileOpen {

	private final JFileChooser fileChooser = new JFileChooser();
	public String filePath = null;

	/**
	 * Create the application.
	 */
	public FileOpen() {
		filePath = initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String initialize() {
		String selectFilePath = "";
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			selectFilePath = selectedFile.getAbsolutePath();
		}
		return selectFilePath;
	}

}
