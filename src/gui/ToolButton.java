package gui;

import command.Command;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utility.ResourceLoader;

public class ToolButton extends ImageView {

	public ToolButton(Command command) {
		this.setImage(
				ResourceLoader.loadImage(command.getImagePath(), GUISetting.PICTURE_WIDTH, GUISetting.PICTURE_HEIGHT));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				command.use();
			}
		});
	}

}