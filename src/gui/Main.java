package gui;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		InitializeGUI();
	}
	
	private void InitializeGUI(){
		VBox root = new VBox();
		
		//Add the summary label to the root
		root.getChildren().add(summaryLabel);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Download Manager");
		primaryStage.show();
		
	}
}
