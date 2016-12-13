package command.edit_command;

import java.util.Optional;

import command.Command;
import exception.InvalidVertexException;
import gui.GUISetting;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import logic.GraphData;
import logic.Vertex;
import utility.ResourceLoader;

public class AddVertexCommand extends Command {

	private static AddVertexCommand instance = new AddVertexCommand();

	public static AddVertexCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.ADD_VERTEX_IMAGE_PATH;
	}

	boolean isInvalid(TextField name) {
		return name.getText().trim().isEmpty();
	}

	@Override
	public void use() {
		Dialog<Pair<String, Boolean>> dialog = new Dialog<>();

		TextField name;
		CheckBox isSelected;

		dialog.setTitle("Add Vertex");
		dialog.setHeaderText("Enter Vertex Information.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

		name = new TextField();
		isSelected = new CheckBox();

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Name :"), 0, 0);
		grid.add(name, 1, 0);
		grid.add(new Label("Selected :"), 0, 1);
		grid.add(isSelected, 1, 1);

		Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		name.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				addButton.setDisable(isInvalid(name));
			}
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> name.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == addButtonType) {
				return new Pair<>(name.getText().trim(), isSelected.isSelected());
			}
			return null;
		});

		Optional<Pair<String, Boolean>> result = dialog.showAndWait();
		result.ifPresent(vertexData -> {
			try {
				GraphData.getInstance().addVertex(new Vertex(vertexData.getKey(), vertexData.getValue()));
			} catch (InvalidVertexException e) {
				e.showAlert();
			}
		});
	}

}
