package command.file_command;

import java.io.File;
import java.io.IOException;

import command.Command;
import gui.GUISetting;
import gui.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import logic.GraphData;

public class SaveCommand extends Command {

	private static SaveCommand instance = new SaveCommand();

	public static SaveCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.SAVE_IMAGE_PATH;
	}

	@Override
	public void use() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Simulation");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("GSIM (Graph Simulation)", "*.gsim"));
		File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
		if (file != null) {
			try {
				GraphData.save(file);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText(null);
				alert.setContentText("Error Loading Graph Data");
				alert.showAndWait();
			}
		}
	}

}
