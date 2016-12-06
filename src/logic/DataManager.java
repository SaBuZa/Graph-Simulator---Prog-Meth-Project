package logic;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.paint.Color;

public class DataManager {

	private static final DataManager instance;
	private CopyOnWriteArrayList<Vertex> vertices; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	private CopyOnWriteArrayList<Color> VColorList; //Vertex's color list
	private CopyOnWriteArrayList<Color> EColorList; //Edge's color List
	private boolean isValid;
	private boolean isTree;
	
	public DataManager(){
		vertices = new CopyOnWriteArrayList<Vertex>();
		edges = new CopyOnWriteArrayList<Edge>();
		isValid = false;
		isTree = false;
	}
	
	static{
		instance = new DataManager();
	}
	
	public static DataManager getInstance(){
		return instance;
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isTree() {
		return isTree;
	}

	public void setTree(boolean isTree) {
		this.isTree = isTree;
	}

	public CopyOnWriteArrayList<Vertex> getVertices() {
		return vertices;
	}

	public CopyOnWriteArrayList<Edge> getEdges() {
		return edges;
	}
	
}
