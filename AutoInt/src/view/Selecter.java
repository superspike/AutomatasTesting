package view;

import javafx.stage.FileChooser;

public class Selecter {
	public FileChooser fc;
	public static FileChooser createFileChooser(){
		return new FileChooser();
	}
}
