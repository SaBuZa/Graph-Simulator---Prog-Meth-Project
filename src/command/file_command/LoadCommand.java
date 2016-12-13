package command.file_command;

import java.io.File;
import java.io.IOException;

import command.ConfirmativeCommand;
import exception.DeserializeFailException;
import gui.GUISetting;
import gui.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import logic.GraphData;

public class LoadCommand extends ConfirmativeCommand {

	private static LoadCommand instance = new LoadCommand();

	public static LoadCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.LOAD_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Load Simulation";
	}

	protected String getContentText() {
		return "Unsaved data will be lost.\nAre you sure?";
	}

	protected void operate() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Simulation");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("GSIM (Graph Simulation)", "*.gsim"));
		File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
		if (file != null) {
			try {
				GraphData.load(file);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText(null);
				alert.setContentText("Error Loading Graph Data");
				alert.showAndWait();
			} catch (DeserializeFailException e) {
				e.showAlert();
			}
		}
	}

}
