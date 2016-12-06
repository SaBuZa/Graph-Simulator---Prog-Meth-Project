package gui;

import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.DataManager;
import logic.Edge;
import logic.Vertex;

public class SimulatorScreen extends Canvas{
	private static int CIRCLE_RADIUS=5;
	private int screen_height,screen_width;
	private CopyOnWriteArrayList<Vertex> vertices; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	private CopyOnWriteArrayList<Color> VColorList;
	private CopyOnWriteArrayList<Color> EColorList;
	
	GraphicsContext gc = getGraphicsContext2D();
	
	public SimulatorScreen (int width, int height){
		super(width, height);
		this.resize(width, height);
		vertices = DataManager.getInstance().getVertices();
		edges = DataManager.getInstance().getEdges();
		screen_height = height;
		screen_width = width;
		
		vertices.add(new Vertex(1,1,10,10));
		Color col = Color.AZURE;
		VColorList.add(col);
	}
	
	public void drawAll(){
		drawAllVertices();
		drawAllEdges();
	}
	
	public void drawAllVertices(){
		for (Vertex v : vertices){
			drawVertex(v.getX(),v.getY(),v.getColorID());
		}
	}
	
	public void drawAllEdges(){
		for (Edge e : edges){
			drawEdge(e.getFrom(),e.getTo(),e.getColorID());
		}
	}
	
	public void drawVertex(int x,int y, int colorID){
		Color color = VColorList.get(colorID);
		gc.setFill(color);
		gc.fillOval(x, y, CIRCLE_RADIUS, CIRCLE_RADIUS);
	}
	
	public void drawEdge(Vertex u, Vertex v, int colorID){//Always do this before vertex
		Color color = EColorList.get(colorID);
		gc.setFill(color);
		gc.strokeLine(u.getX(), u.getY(), v.getX(), v.getY());
	}
	
	
}
