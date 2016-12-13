package command.edit_command;

import java.util.Collections;
import java.util.Optional;

import command.Command;
import exception.EdgeNotFoundException;
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
import logic.SettingData;
import utility.ResourceLoader;

public class EditEdgeCommand extends Command {

	private static EditEdgeCommand instance = new EditEdgeCommand();

	public static EditEdgeCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.EDIT_EDGE_IMAGE_PATH;
	}

	class EditEdgeData {
		public String from;
		public String to;
		public double cost;
		public boolean isSelected;

		public EditEdgeData(String from, String to, double cost, boolean isSelected) {
			this.from = from;
			this.to = to;
			this.cost = cost;
			this.isSelected = isSelected;
		}
	}

	boolean isInvalid(TextField cost) {
		return !cost.getText().trim().matches("^[0-9]+$");
	}

	@Override
	public void use() {
		Dialog<EditEdgeData> dialog = new Dialog<>();

		ChoiceBox<String> from, to;
		TextField cost;
		CheckBox isSelected;

		dialog.setTitle("Edit Edge");
		dialog.setHeaderText("Enter Edge Information.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));

		ButtonType addButtonType = new ButtonType("Apply", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

		from = new ChoiceBox<String>();
		to = new ChoiceBox<String>();
		cost = new TextField();
		isSelected = new CheckBox();

		if (GraphData.getInstance().getEdges().size() < 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Edit Edge");
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
		grid.add(new Label("Cost :"), 0, 2);
		grid.add(cost, 1, 2);
		grid.add(new Label("Selected :"), 0, 3);
		grid.add(isSelected, 1, 3);

		Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		cost.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				addButton.setDisable(isInvalid(cost));
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
				return new EditEdgeData(from.getValue(), to.getValue(), Double.parseDouble(cost.getText().trim()),
						isSelected.isSelected());
			}
			return null;
		});

		Optional<EditEdgeData> result = dialog.showAndWait();
		result.ifPresent(edgeData -> {
			try {
				GraphData.getInstance().editEdge(edgeData.from, edgeData.to, edgeData.cost, edgeData.isSelected);
			} catch (VertexNotFoundException e) {
				e.showAlert();
			} catch (EdgeNotFoundException e) {
				e.showAlert();
			}
		});
	}

}
