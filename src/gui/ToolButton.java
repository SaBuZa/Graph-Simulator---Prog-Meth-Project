package gui;

import command.Command;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utility.ResourceLoader;

public class ToolButton extends ImageView {

	public ToolButton(Command command, double size) {
		this.setImage(ResourceLoader.loadImage(command.getImagePath(), size, size));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				command.use();
			}
		});
	}

}