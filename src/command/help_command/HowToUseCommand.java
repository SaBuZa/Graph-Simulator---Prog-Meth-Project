package command.help_command;

import command.Command;
import gui.GUISetting;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import utility.ResourceLoader;

public class HowToUseCommand extends Command {

	private static HowToUseCommand instance = new HowToUseCommand();

	public static HowToUseCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.HOW_TO_USE_IMAGE_PATH;
	}

	@Override
	public void use() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("How To Use");
		alert.setHeaderText(null);
		alert.setGraphic(null);
		
		ImageView addVertex = new ImageView(ResourceLoader.loadImage(GUISetting.ADD_VERTEX_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView addEdge = new ImageView(ResourceLoader.loadImage(GUISetting.ADD_EDGE_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView editVertex = new ImageView(ResourceLoader.loadImage(GUISetting.EDIT_VERTEX_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView editEdge = new ImageView(ResourceLoader.loadImage(GUISetting.EDIT_EDGE_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView deleteVertex = new ImageView(ResourceLoader.loadImage(GUISetting.DELETE_VERTEX_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView deleteEdge = new ImageView(ResourceLoader.loadImage(GUISetting.DELETE_EDGE_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		ImageView setting = new ImageView(ResourceLoader.loadImage(GUISetting.SETTING_IMAGE_PATH, GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(addVertex, 0, 0);
		grid.add(new Label("Add a new vertex"),1,0);
		grid.add(addEdge, 0, 1);
		grid.add(new Label("Add a new edge"),1,1);
		grid.add(editVertex, 0, 2);
		grid.add(new Label("Edit a vertex"),1,2);
		grid.add(editEdge, 0, 3);
		grid.add(new Label("Edit an edge"),1,3);
		grid.add(deleteVertex, 0, 4);
		grid.add(new Label("Delete a vertex"),1,4);
		grid.add(deleteEdge, 0, 5);
		grid.add(new Label("Delete an edge"),1,5);
		grid.add(setting, 0, 6);
		grid.add(new Label("Setting"),1,6);
		
		alert.getDialogPane().setContent(grid);
		alert.showAndWait();
	}

}
