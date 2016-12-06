package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ToolBox extends GridPane{
	private int height,width;
	
	public ToolBox(){
		
		//Set ToolBox Properties
		height = Main.SCREEN_HEIGHT;
		width = Main.SCREEN_WIDTH/10;
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		this.setPadding(new Insets(10,10,10,10));
		this.setHgap(10);
		this.setVgap(10);
		
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
		
		//brushBtn.setMaxSize(btnWidth, btnHeight);
		Image image = new Image(Main.class.getResource("/images/testBrush.png").toExternalForm(),btnWidth/2,btnHeight/2,false,false);
		ImageView imageView = new ImageView(image);
		brushBtn.setGraphic(imageView);
		//brushBtn.setGraphic(new ImageView("/images/testBrush.png"));
		
		//this.getItems().addAll(b1, b2, brushBtn);
		this.add(b1, 1, 1);
		this.add(b2, 2, 1);
		this.add(brushBtn, 1, 2);
	}
}
