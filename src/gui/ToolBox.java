package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

public class ToolBox extends ToolBar{
	private int height,width;
	
	public ToolBox(){
		
		
		//Set ToolBox Properties
		height = Main.SCREEN_HEIGHT;
		width = Main.SCREEN_WIDTH/10;
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		this.
		
		//Set Initial Values
		int btnHeight = height/15;
		int btnWidth = width/3;
		
		//Create Components
		Button b1 = new Button();
		Button b2 = new Button();
		Button brushBtn = new Button();
		
		//Set Components' Properties
		b1.setPrefHeight(btnHeight);
		b1.setPrefWidth(btnWidth);
		b2.setPrefHeight(btnHeight);
		b2.setPrefWidth(btnWidth);
		brushBtn.setPrefHeight(btnHeight);
		brushBtn.setPrefWidth(btnWidth);
		brushBtn.setPrefHeight(btnHeight);
		brushBtn.setPrefWidth(btnWidth);
		//brushBtn.setGraphic(new ImageView("/images/testBrush.png"));
		
		this.getItems().addAll(b1, b2, brushBtn);
	}
}
