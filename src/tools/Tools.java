package tools;

import java.util.concurrent.CopyOnWriteArrayList;

import logic.Edge;
import logic.Vertex;

//CONTAIN BASIC OPERATION FOR OUR GRAPH?
public abstract class Tools {
	private CopyOnWriteArrayList<Vertex> vertices; //Always Sorted?
	private CopyOnWriteArrayList<Edge> edges; //Always Sorted?
	public abstract void use();
}
