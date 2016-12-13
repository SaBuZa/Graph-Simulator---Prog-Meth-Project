package command.edit_command;

import java.util.Collections;
import java.util.Optional;

import command.Command;
import exception.EdgeNotFoundException;
import exception.VertexNotFoundException;
import gui.GUISetting;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.GraphData;
import utility.ResourceLoader;

public class DeleteVertexCommand extends Command {

	private static DeleteVertexCommand instance = new DeleteVertexCommand();

	public static DeleteVertexCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.DELETE_VERTEX_IMAGE_PATH;
	}

	@Override
	public void use() {
		Dialog<String> dialog = new Dialog<>();

		ChoiceBox<String> name;

		dialog.setTitle("Delete Vertex");
		dialog.setHeaderText("Select Vertex.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		name = new ChoiceBox<String>();

		try {
			ObservableList<String> verticesName = GraphData.getInstance().getVerticesName();
			Collections.sort(verticesName);
			name.setItems(verticesName);
			name.setValue(verticesName.get(0));
		} catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Delete Vertex");
			alert.setHeaderText(null);
			alert.setContentText("At least 1 vertex must be present.");
			alert.showAndWait();
			return;
		}

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Name :"), 0, 0);
		grid.add(name, 1, 0);

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> name.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return name.getValue();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(vertexData -> {
			try {
				GraphData.getInstance().deleteVertex(vertexData);
			} catch (VertexNotFoundException e) {
				e.showAlert();
			} catch (EdgeNotFoundException e) {
				e.showAlert();
			}
		});
	}

}
