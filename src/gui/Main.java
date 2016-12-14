package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import thread.ThreadHolder;

public class Main extends Application {

	private static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		BorderPane root = new BorderPane();
		root.setTop(new MenuBox());
		ToolBox toolBox = new ToolBox();
		root.setLeft(toolBox);
		root.setCenter(SimulatorScreen.getInstance());
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Graph Simulator");
		primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		primaryStage.setResizable(false);
		primaryStage.show();
		toolBox.initialize();
		SimulatorScreen.getInstance().initialize();
	}

	public void stop() {
		ThreadHolder.interruptAllThreads();
	}

}
