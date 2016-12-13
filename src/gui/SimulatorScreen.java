package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SimulatorScreen extends StackPane {

	private static SimulatorScreen instance = new SimulatorScreen();
	private SimulatorLayer edgesLayer;
	private SimulatorLayer edgesLabelLayer;
	private SimulatorLayer verticesLayer;
	private SimulatorLayer verticesLabelLayer;

	public SimulatorScreen() {
		edgesLayer = new SimulatorLayer();
		edgesLayer.setPickOnBounds(false);
		edgesLabelLayer = new SimulatorLayer();
		edgesLabelLayer.setPickOnBounds(false);
		verticesLayer = new SimulatorLayer();
		verticesLayer.setPickOnBounds(false);
		verticesLabelLayer = new SimulatorLayer();
		verticesLabelLayer.setPickOnBounds(false);
		this.getChildren().addAll(edgesLayer, edgesLabelLayer, verticesLayer, verticesLabelLayer);
		this.setAlignment(Pos.TOP_LEFT);
		this.setStyle("-fx-background-color: white");
	}

	public static SimulatorScreen getInstance() {
		return instance;
	}

	public void initailize() {
		this.setClip(new Rectangle(getWidth(), getHeight()));
	}

	public void addEdge(Node node) {
		edgesLayer.getChildren().add(node);
	}

	public void addEdgeLabel(Node node) {
		edgesLabelLayer.getChildren().add(node);
	}

	public void addVertex(Node node) {
		verticesLayer.getChildren().add(node);
	}

	public void addVertexLabel(Node node) {
		verticesLabelLayer.getChildren().add(node);
	}

	public void removeEdge(Node node) {
		edgesLayer.getChildren().remove(node);
	}

	public void removeEdgeLabel(Node node) {
		edgesLabelLayer.getChildren().remove(node);
	}

	public void removeVertex(Node node) {
		verticesLayer.getChildren().remove(node);
	}

	public void removeVertexLabel(Node node) {
		verticesLabelLayer.getChildren().remove(node);
	}

	public void clear() {
		edgesLayer.getChildren().clear();
		edgesLabelLayer.getChildren().clear();
		verticesLayer.getChildren().clear();
		verticesLabelLayer.getChildren().clear();
	}

}
