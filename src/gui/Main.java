package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static final int SCREEN_WIDTH=1280,SCREEN_HEIGHT=720;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		InitializeGUI(primaryStage);
		Stage secondaryStage = new Stage();
		
		secondaryStage.initModality(Modality.APPLICATION_MODAL);
		secondaryStage.initOwner(primaryStage);
		
		/*
		Popup popo = new Popup();
		popo.getContent().addAll(new Circle(25,25,50,Color.BLUE));
		popo.show(primaryStage);
		popo.hide();
		*/
	}
	
	private void InitializeGUI(Stage primaryStage){
		BorderPane root = new BorderPane();
		
		//Add the summary label to the root
		//root.getChildren().add(summaryLabel);
		
		
		//MenuBar
		MenuBar menuBar = new MenuBar();
		
		//File Menu 
		Menu fileMenu = new Menu("File");
		
		//File Menu Items
		MenuItem newItem = new MenuItem("New");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem settingItem = new MenuItem("Setting");
		MenuItem closeItem = new MenuItem("Close");
		
		//Edit Menu
		Menu editMenu = new Menu("Edit");
		
		//Edit Menu Items
		MenuItem cutItem = new MenuItem("Cut");
		MenuItem copyItem = new MenuItem("Copy");
		MenuItem pasteItem = new MenuItem("Paste");
		
		//Help Menu
		Menu helpMenu = new Menu("Help");
		
		//Help Menu Items
		MenuItem howToUseItem = new MenuItem("How To Use");
		MenuItem aboutUsItem = new MenuItem("About Us");
		
		//Add all items to Menu
		fileMenu.getItems().addAll(newItem,saveItem, settingItem, closeItem);
		editMenu.getItems().addAll(cutItem,copyItem,pasteItem);
		helpMenu.getItems().addAll(howToUseItem, aboutUsItem);
		
		//Add Menus to MenuBar
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		
		//Create Toolbox and SimulatorScreen
		ToolBox toolBox = new ToolBox();
		StackPane centerPane = new StackPane();
		SimulatorScreen simScreen = new SimulatorScreen(SCREEN_WIDTH,SCREEN_HEIGHT);
		centerPane.getChildren().add(simScreen);
		centerPane.setStyle("-fx-background-color: white");
		
		/*
		 * Do Everything in ToolBox Class instead
		 * 
		//Create button for Toolbox
		Button b1 = new Button("Test1");
		Button b2 = new Button("Test2");
		
		//Add button to Toolbox
		*/
		
		root.setTop(menuBar);
		root.setLeft(toolBox);
		root.setCenter(centerPane);
		//root.setCenter(simScreen);
		/*
		root.setTop(value);
		root.setBottom(value);
		root.setLeft(value);
		root.setRight(value);
		root.setCenter(value);
		*/
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Graph Simulator");
		primaryStage.show();
		
	}
}
