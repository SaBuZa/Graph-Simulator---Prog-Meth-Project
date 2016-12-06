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
	private static int CIRCLE_RADIUS=20;
	private int screen_height,screen_width;
	private CopyOnWriteArrayList<Vertex> vertices; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	private CopyOnWriteArrayList<Color> VColorList;
	private CopyOnWriteArrayList<Color> EColorList;
	
	GraphicsContext gc;
	
	public SimulatorScreen (int width, int height){
		super(width, height);
		this.resize(width, height);
		vertices = DataManager.getInstance().getVertices();
		edges = DataManager.getInstance().getEdges();
		screen_height = height;
		screen_width = width;
		gc = getGraphicsContext2D();

		//FOR TESTING
		Vertex v1,v2;
		v1 = new Vertex(1,1,10,10);
		v2 = new Vertex(2,2,100,100);
		vertices.add(v1);
		vertices.add(v2);
		edges.add(new Edge(1,v1,v2));
		drawAll();
	}
	
	public void drawAll(){
		drawAllEdges();
		drawAllVertices();
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
		//Color color = VColorList.get(colorID);
		Color color = Color.WHITE;
		gc.setFill(color);
		gc.fillOval(x, y, 2*CIRCLE_RADIUS, 2*CIRCLE_RADIUS);
		
		//gc.fillRect(10, 10, 100, 100);
		System.out.println(x + " " + y + " " + CIRCLE_RADIUS);
	}
	
	public void drawEdge(Vertex u, Vertex v, int colorID){//Always do this before vertex
		//Color color = EColorList.get(colorID);
		Color color = Color.WHITE;
		gc.setFill(color);
		gc.strokeLine(u.getX() + CIRCLE_RADIUS, u.getY() + CIRCLE_RADIUS, v.getX() + CIRCLE_RADIUS, v.getY() + CIRCLE_RADIUS);
	}
	
	
}
