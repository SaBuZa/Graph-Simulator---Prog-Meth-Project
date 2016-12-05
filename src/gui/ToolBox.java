package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

public class ToolBox extends ToolBar{
	public ToolBox(){
		Button b1 = new Button();
		Button b2 = new Button();
		Button brushBtn = new Button();
		brushBtn.setGraphic(new ImageView("/images/testBrush.png"));
		
		this.getItems().addAll(b1, b2, brushBtn);
	}
}
