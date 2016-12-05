package gui;

import javafx.scene.canvas.Canvas;

public class SimulatorScreen extends Canvas{
	public static int screen_width, screen_height;
	
	public SimulatorScreen (int width, int height){
		super(width, height);
		this.resize(width, height);
	}
	
	
}
