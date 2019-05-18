package sdvEditorGUI;

import java.io.File;
import javax.swing.JFileChooser;

public class FileSave {

	private final JFileChooser fileChooser = new JFileChooser();
	public String filePath = null;

	/**
	 * Create the application.
	 */
	public FileSave() {
		filePath = initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public String initialize() {
		String selectFilePath = "";
		int result = fileChooser.showSaveDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			selectFilePath = selectedFile.getAbsolutePath();
		}
		return selectFilePath;
	}

}
