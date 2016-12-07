package gui;

import java.util.concurrent.CopyOnWriteArrayList;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import logic.DataManager;
import logic.Edge;
import logic.Vertex;

public class SimulatorScreen extends FlowPane {
	private static int CIRCLE_RADIUS=20;
	private int screen_height,screen_width;
	private CopyOnWriteArrayList<Vertex> vertices; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	private CopyOnWriteArrayList<Color> VColorList;
	private CopyOnWriteArrayList<Color> EColorList;
	private double posX, posY,offsetX,offsetY,transX,transY;
	
	public SimulatorScreen (int width, int height){
		super(width, height);
		this.resize(width, height);
		System.out.println(("-> " + this.getLocalToSceneTransform().getTx() + " " +  this.getLayoutY()));
		
		vertices = DataManager.getInstance().getVertices();
		edges = DataManager.getInstance().getEdges();
		screen_height = height;
		screen_width = width;
		//FOR TESTING
		Vertex v1,v2;
		v1 = new Vertex(1,1,10,10);
		v2 = new Vertex(2,2,0,0);
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
	
	public void drawVertex(double x,double y, int colorID){
		//Color color = VColorList.get(colorID);
		Color color = Color.DARKBLUE;
		Circle circle = new Circle(CIRCLE_RADIUS, color);
		circle.setCursor(Cursor.HAND);
		
		//Set Event Control
		//circle.setOnMousePressed(circleOnMousePressedEventHandler);
		circle.setOnMousePressed(circleOnMousePressedEventHandler);
		circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		
		//circle.boundsInLocalProperty();

		circle.setTranslateX(x);
		circle.setTranslateY(y);
		
		//gc.fillRect(10, 10, 100, 100);
		System.out.println("CIRCLE : " + x + " " + y + " " + CIRCLE_RADIUS);
		this.getChildren().add(circle);
	}
	
	public void drawEdge(Vertex u, Vertex v, int colorID){//Always do this before vertex
		//Color color = EColorList.get(colorID);
		Color color = Color.BLACK;
		Line line = new Line(u.getX(),u.getY()+100,v.getX(),v.getY());
		this.getChildren().add(line);
	}
	
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent ev) {
			// TODO Auto-generated method stub
			
			posX = ev.getSceneX();
			posY = ev.getSceneY();
			transX = ((Circle)(ev.getSource())).getTranslateX();
			transY = ((Circle)(ev.getSource())).getTranslateY();
			
		}
		
		
	};
	
	
	
    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
     
            @Override
            public void handle(MouseEvent ev) {
            	Circle circle = (Circle)(ev.getSource());
                double offsetX = ev.getSceneX() - posX;
                double offsetY = ev.getSceneY() - posY;
                double newX = circle.getTranslateX() + offsetX;
                double newY = circle.getTranslateY() + offsetY;
                posX += offsetX;
                posY += offsetY;

                circle.setTranslateX(newX);
                circle.setTranslateY(newY);
            }
        };
}
