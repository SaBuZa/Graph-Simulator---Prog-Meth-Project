package command.file_command;

import java.util.Optional;

import command.Command;
import gui.GUISetting;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.SettingData;
import utility.ResourceLoader;

public class SettingCommand extends Command {

	private static SettingCommand instance = new SettingCommand();

	public static SettingCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.SETTING_IMAGE_PATH;
	}

	class SettingUpdateData {
		public int vertexRadius;
		public int vertexFontSize;
		public int edgeFontSize;
		public boolean isShowEdgeCost;

		public SettingUpdateData(int vertexRadius, int vertexFontSize, int edgeFontSize, boolean isShowEdgeCost) {
			this.vertexRadius = vertexRadius;
			this.vertexFontSize = vertexFontSize;
			this.edgeFontSize = edgeFontSize;
			this.isShowEdgeCost = isShowEdgeCost;
		}
	}

	@Override
	public void use() {
		Dialog<SettingUpdateData> dialog = new Dialog<>();

		dialog.setTitle("Setting");
		dialog.setHeaderText("Choose Your Preferences.");
		dialog.setGraphic(new ImageView(
				ResourceLoader.loadImage(getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT)));
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		Slider vertexRadius;
		Slider vertexFontSize;
		Slider edgeFontSize;
		CheckBox isShowEdgeCost;

		Label vertexRadiusShow;
		Label vertexFontSizeShow;
		Label edgeFontSizeShow;

		Button vertexRadiusDefault;
		Button vertexFontSizeDefault;
		Button edgeFontSizeDefault;

		SettingUpdateData oldSetting;

		oldSetting = new SettingUpdateData(SettingData.getInstance().getVertexRadius(),
				SettingData.getInstance().getVertexFontSize(), SettingData.getInstance().getEdgeFontSize(),
				SettingData.getInstance().isShowEdgeCost());

		vertexRadius = new Slider(SettingData.VERTEX_RADIUS_MIN, SettingData.VERTEX_RADIUS_MAX,
				oldSetting.vertexRadius);
		vertexFontSize = new Slider(SettingData.VERTEX_FONT_SIZE_MIN, SettingData.VERTEX_FONT_SIZE_MAX,
				oldSetting.vertexFontSize);
		edgeFontSize = new Slider(SettingData.EDGE_FONT_SIZE_MIN, SettingData.EDGE_FONT_SIZE_MAX,
				oldSetting.edgeFontSize);
		isShowEdgeCost = new CheckBox();
		isShowEdgeCost.setSelected(oldSetting.isShowEdgeCost);

		vertexRadius.setPrefWidth(200);
		vertexFontSize.setPrefWidth(200);
		edgeFontSize.setPrefWidth(200);

		vertexRadiusShow = new Label(
				Integer.toString(new Double(SettingData.getInstance().getVertexRadius()).intValue()));
		vertexFontSizeShow = new Label(
				Integer.toString(new Double(SettingData.getInstance().getVertexFontSize()).intValue()));
		edgeFontSizeShow = new Label(
				Integer.toString(new Double(SettingData.getInstance().getEdgeFontSize()).intValue()));

		vertexRadius.valueProperty().addListener((obs, oldval, newVal) -> {
			vertexRadius.setValue(newVal.intValue());
			vertexRadiusShow.setText(Integer.toString(newVal.intValue()));
			SettingData.getInstance().setVertexRadius(newVal.intValue());
		});
		vertexFontSize.valueProperty().addListener((obs, oldval, newVal) -> {
			vertexFontSize.setValue(newVal.intValue());
			vertexFontSizeShow.setText(Integer.toString(newVal.intValue()));
			SettingData.getInstance().setVertexFontSize(newVal.intValue());
		});
		edgeFontSize.valueProperty().addListener((obs, oldval, newVal) -> {
			edgeFontSize.setValue(newVal.intValue());
			edgeFontSizeShow.setText(Integer.toString(newVal.intValue()));
			SettingData.getInstance().setEdgeFontSize(newVal.intValue());
		});
		isShowEdgeCost.selectedProperty().addListener((obs, oldval, newVal) -> {
			SettingData.getInstance().setShowEdgeCost(newVal);
		});

		vertexRadiusShow.setPrefWidth(50);
		vertexFontSizeShow.setPrefWidth(50);
		edgeFontSizeShow.setPrefWidth(50);

		vertexRadiusDefault = new Button("Default");
		vertexFontSizeDefault = new Button("Default");
		edgeFontSizeDefault = new Button("Default");

		vertexRadiusDefault.setOnAction((e) -> {
			vertexRadius.setValue(SettingData.VERTEX_RADIUS_DEFAULT);
		});
		vertexFontSizeDefault.setOnAction((e) -> {
			vertexFontSize.setValue(SettingData.VERTEX_FONT_SIZE_DEFAULT);
		});
		edgeFontSizeDefault.setOnAction((e) -> {
			edgeFontSize.setValue(SettingData.EDGE_FONT_SIZE_DEFAULT);
		});

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(new Label("Vertex Radius :"), 0, 0);
		grid.add(vertexRadius, 1, 0);
		grid.add(vertexRadiusShow, 2, 0);
		grid.add(vertexRadiusDefault, 3, 0);
		grid.add(new Label("Vertex Font Size :"), 0, 1);
		grid.add(vertexFontSize, 1, 1);
		grid.add(vertexFontSizeShow, 2, 1);
		grid.add(vertexFontSizeDefault, 3, 1);
		grid.add(new Label("Edge Font Size :"), 0, 2);
		grid.add(edgeFontSize, 1, 2);
		grid.add(edgeFontSizeShow, 2, 2);
		grid.add(edgeFontSizeDefault, 3, 2);
		grid.add(new Label("Show Edge Cost :"), 0, 3);
		grid.add(isShowEdgeCost, 1, 3);

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> vertexRadius.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new SettingUpdateData(new Double(vertexRadius.getValue()).intValue(),
						new Double(vertexFontSize.getValue()).intValue(),
						new Double(edgeFontSize.getValue()).intValue(), isShowEdgeCost.isSelected());
			}
			return null;
		});

		Optional<SettingUpdateData> result = dialog.showAndWait();
		if (result.isPresent()) {
			SettingData.getInstance().setVertexRadius(result.get().vertexRadius);
			SettingData.getInstance().setVertexFontSize(result.get().vertexFontSize);
			SettingData.getInstance().setEdgeFontSize(result.get().edgeFontSize);
			SettingData.getInstance().setShowEdgeCost(result.get().isShowEdgeCost);
		} else {
			SettingData.getInstance().setVertexRadius(oldSetting.vertexRadius);
			SettingData.getInstance().setVertexFontSize(oldSetting.vertexFontSize);
			SettingData.getInstance().setEdgeFontSize(oldSetting.edgeFontSize);
			SettingData.getInstance().setShowEdgeCost(oldSetting.isShowEdgeCost);
		}

	}

}
