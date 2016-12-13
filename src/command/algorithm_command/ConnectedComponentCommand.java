package command.algorithm_command;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import algorithm.ConnectedComponent;
import command.ConfirmativeCommand;
import exception.VertexNotFoundException;
import gui.GUISetting;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import logic.GraphData;

public class ConnectedComponentCommand extends ConfirmativeCommand {

	private static ConnectedComponentCommand instance = new ConnectedComponentCommand();

	public static ConnectedComponentCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.CONNECTED_COMPONENT_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Connected Component";
	}

	protected String getContentText() {
		return "This will change nodes and edges Selected property. Proceed?";
	}

	protected void operate() {
		try {
			List<String> verticesName = GraphData.getInstance().getVerticesName();
			Collections.sort(verticesName);
			ChoiceDialog<String> dialog = new ChoiceDialog<>(verticesName.get(0), verticesName);
			dialog.setTitle("Connected Component");
			dialog.setHeaderText(null);
			dialog.setContentText("Select Source Vertex.");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(vertex -> {
				try {
					ConnectedComponent.getInstance().setSource(GraphData.getInstance().getVertex(vertex));
					ConnectedComponent.getInstance().solve();
				} catch (VertexNotFoundException e) {
					e.showAlert();
				}
			});
		} catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connected Component");
			alert.setHeaderText(null);
			alert.setContentText("At least 1 vertices must be present.");
			alert.showAndWait();
			return;
		}
	}

}
