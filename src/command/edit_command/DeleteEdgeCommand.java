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
import javafx.util.Pair;
import logic.GraphData;
import utility.ResourceLoader;

public class DeleteEdgeCommand extends Command {

	private static DeleteEdgeCommand instance = new DeleteEdgeCommand();

	public static DeleteEdgeCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.DELETE_EDGE_IMAGE_PATH;
	}

	@Override
	public void use() {
		Dialog<Pair<String, String>> dialog = new Dialog<>();

		ChoiceBox<String> from;
		ChoiceBox<String> to;

		dialog.setTitle("Delete Edge");
		dialog.setHeaderText("Select Vertices");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		from = new ChoiceBox<String>();
		to = new ChoiceBox<String>();

		if (GraphData.getInstance().getEdges().size() < 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Delete Edge");
			alert.setHeaderText(null);
			alert.setContentText("At least 1 edge must be present.");
			alert.showAndWait();
			return;
		}

		ObservableList<String> verticesName = GraphData.getInstance().getVerticesName();
		Collections.sort(verticesName);
		from.setItems(verticesName);
		from.setValue(verticesName.get(0));
		to.setItems(verticesName);
		to.setValue(verticesName.get(1));

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("From :"), 0, 0);
		grid.add(from, 1, 0);
		grid.add(new Label("To :"), 0, 1);
		grid.add(to, 1, 1);

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> from.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new Pair<>(from.getValue(), to.getValue());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(edgeData -> {
			try {
				GraphData.getInstance().deleteEdge(edgeData.getKey(), edgeData.getValue());
			} catch (VertexNotFoundException e) {
				e.showAlert();
			} catch (EdgeNotFoundException e) {
				e.showAlert();
			}
		});
	}

}
