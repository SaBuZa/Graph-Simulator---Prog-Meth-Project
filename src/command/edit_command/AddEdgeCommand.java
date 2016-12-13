package command.edit_command;

import java.util.Collections;
import java.util.Optional;

import command.Command;
import exception.InvalidEdgeException;
import exception.VertexNotFoundException;
import gui.GUISetting;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import logic.Edge;
import logic.GraphData;
import logic.SettingData;
import utility.ResourceLoader;

public class AddEdgeCommand extends Command {

	private static AddEdgeCommand instance = new AddEdgeCommand();

	public static AddEdgeCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.ADD_EDGE_IMAGE_PATH;
	}

	class AddEdgeData {
		public String from;
		public String to;
		public double cost;
		public boolean isSelected;

		public AddEdgeData(String from, String to, double cost, boolean isSelected) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			this.isSelected = isSelected;
		}
	}

	@Override
	public void use() {
		Dialog<AddEdgeData> dialog = new Dialog<>();

		ChoiceBox<String> from, to;
		TextField cost;
		CheckBox isSelected;

		dialog.setTitle("Add Edge");
		dialog.setHeaderText("Enter Edge Information.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

		from = new ChoiceBox<String>();
		to = new ChoiceBox<String>();
		cost = new TextField();
		isSelected = new CheckBox();

		try {
			ObservableList<String> verticesName = GraphData.getInstance().getVerticesName();
			Collections.sort(verticesName);
			from.setItems(verticesName);
			from.setValue(verticesName.get(0));
			to.setItems(verticesName);
			to.setValue(verticesName.get(1));
		} catch (IndexOutOfBoundsException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Add Edge");
			alert.setHeaderText(null);
			alert.setContentText("At least 2 vertices must be present.");
			alert.showAndWait();
			return;
		}

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("From :"), 0, 0);
		grid.add(from, 1, 0);
		grid.add(new Label("To :"), 0, 1);
		grid.add(to, 1, 1);
		grid.add(new Label("Cost :"), 0, 2);
		grid.add(cost, 1, 2);
		grid.add(new Label("Selected :"), 0, 3);
		grid.add(isSelected, 1, 3);

		Node addButton = dialog.getDialogPane().lookupButton(addButtonType);

		cost.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				addButton.setDisable(!cost.getText().trim().matches("^[0-9]+$"));
			}
		});

		if (SettingData.getInstance().isShowEdgeCost()) {
			addButton.setDisable(true);
		} else {
			cost.setText("0");
			cost.setDisable(true);
		}

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> from.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == addButtonType) {
				return new AddEdgeData(from.getValue().trim(), to.getValue().trim(),
						Double.parseDouble(cost.getText().trim()), isSelected.isSelected());
			}
			return null;
		});

		Optional<AddEdgeData> result = dialog.showAndWait();
		result.ifPresent(edgeData -> {
			try {
				GraphData.getInstance().addEdge(new Edge(GraphData.getInstance().getVertex(edgeData.from),
						GraphData.getInstance().getVertex(edgeData.to), edgeData.cost, edgeData.isSelected));
			} catch (VertexNotFoundException e) {
				e.showAlert();
			} catch (InvalidEdgeException e) {
				e.showAlert();
			}
		});

	}

}
