package command.edit_command;

import java.util.Collections;
import java.util.Optional;

import command.Command;
import exception.InvalidVertexException;
import exception.VertexNotFoundException;
import gui.GUISetting;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import logic.GraphData;
import utility.ResourceLoader;

public class EditVertexCommand extends Command {

	private static EditVertexCommand instance = new EditVertexCommand();

	public static EditVertexCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.EDIT_VERTEX_IMAGE_PATH;
	}

	class EditVertexData {
		public String target;
		public String name;
		public boolean isSelected;

		public EditVertexData(String target, String name, boolean isSelected) {
			this.target = target;
			this.name = name;
			this.isSelected = isSelected;
		}
	}

	boolean isInvalid(TextField name) {
		return name.getText().trim().isEmpty();
	}

	@Override
	public void use() {
		Dialog<EditVertexData> dialog = new Dialog<>();

		ChoiceBox<String> target;
		TextField name;
		CheckBox isSelected;

		dialog.setTitle("Edit Vertex");
		dialog.setHeaderText("Enter Vertex Information.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		ButtonType addButtonType = new ButtonType("Apply", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

		target = new ChoiceBox<String>();
		name = new TextField();
		isSelected = new CheckBox();

		try {
			ObservableList<String> verticesName = GraphData.getInstance().getVerticesName();
			Collections.sort(verticesName);
			target.setItems(verticesName);
			target.setValue(verticesName.get(0));
		} catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Edit Vertex");
			alert.setHeaderText(null);
			alert.setContentText("At least 1 vertex must be present.");
			alert.showAndWait();
			return;
		}

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Target :"), 0, 0);
		grid.add(target, 1, 0);
		grid.add(new Label("Name :"), 0, 1);
		grid.add(name, 1, 1);
		grid.add(new Label("Selected :"), 0, 2);
		grid.add(isSelected, 1, 2);

		Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		name.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				addButton.setDisable(isInvalid(name));
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> target.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == addButtonType) {
				return new EditVertexData(target.getValue(), name.getText().trim(), isSelected.isSelected());
			}
			return null;
		});

		Optional<EditVertexData> result = dialog.showAndWait();
		result.ifPresent(vertexData -> {
			try {
				GraphData.getInstance().editVertex(vertexData.target, vertexData.name, vertexData.isSelected);
			} catch (InvalidVertexException e) {
				e.showAlert();
			} catch (VertexNotFoundException e) {
				e.showAlert();
			}
		});
	}

}
