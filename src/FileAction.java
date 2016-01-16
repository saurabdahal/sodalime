import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class FileAction {

	//boolean newfile;
	String filepath,filename;
	String line = null;
	String NewFileName = "Untitled";
	JFileChooser fc = new JFileChooser();
	int fileChoice;
	//JTextArea textArea;
	


	public FileAction() {
	}

	public void FileOpenaction(Tab ta) {

		fc.setDialogTitle("Sodalime Open Files");
		fileChoice = fc.showOpenDialog(fc);
		ta.newFile = false;
		if (fileChoice == JFileChooser.APPROVE_OPTION) {
			ta.textArea.setLineWrap(true);
			ta.textArea.setWrapStyleWord(true);
			ta.textArea.setText(" ");
			filepath = fc.getSelectedFile().getAbsolutePath();
			
			try {
				FileReader fileReader = new FileReader(filepath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					ta.textArea.append(line);
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void FileSaveaction(Tab ta) {
		fc.setDialogTitle("Sodalime Save Files");
		if (ta.newFile == true) {
			fileChoice = fc.showSaveDialog(fc);
			if (fileChoice == JFileChooser.APPROVE_OPTION) {
				ta.newFile = false;
				filepath = fc.getSelectedFile().getAbsolutePath();
				File userFile = new File(filepath);
				filename = userFile.getName();
				//ta.tabbedPane.setTitleAt(ta.tabbedPane.getSelectedIndex(), filename);
				ta.label.setText(filename);
				try {
					FileWriter fw = new FileWriter(filepath);
					fw.write(ta.textArea.getText());
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		} else {
			try {
				FileWriter fw = new FileWriter(filepath);
				fw.write(ta.textArea.getText());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void FileSaveAsaction(Tab ta) {
		fc.setDialogTitle("Sodalime Save As Files");
		fileChoice = fc.showSaveDialog(fc);
		if (fileChoice == JFileChooser.APPROVE_OPTION) {
			filepath = fc.getSelectedFile().getAbsolutePath();
			try {
				FileWriter fw = new FileWriter(filepath);
				fw.write(ta.textArea.getText());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	public void FileQuitaction() {
		System.exit(0);
	}

}
