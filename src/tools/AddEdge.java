package tools;

import java.util.concurrent.CopyOnWriteArrayList;

import logic.DataManager;
import logic.Edge;
import logic.Vertex;

public class AddEdge extends Tools{

	private CopyOnWriteArrayList<Vertex> vertices = DataManager.getInstance().getVertices(); //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges = DataManager.getInstance().getEdges(); //Always Sorted?
	
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
	
	

}
