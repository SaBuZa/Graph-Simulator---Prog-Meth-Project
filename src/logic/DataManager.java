package logic;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataManager {

	private static final DataManager instance;
	private CopyOnWriteArrayList<Node> nodes; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	private boolean isValid;
	private boolean isTree;
	
	public DataManager(){
		nodes = new CopyOnWriteArrayList<Node>();
		edges = new CopyOnWriteArrayList<Edge>();
		isValid = false;
		isTree = false;
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

	public CopyOnWriteArrayList<Node> getNodes() {
		return nodes;
	}

	public CopyOnWriteArrayList<Edge> getEdges() {
		return edges;
	}
	static{
		instance = new DataManager();
	}
	
	public static DataManager getInstance(){
		return instance;
	}
}
