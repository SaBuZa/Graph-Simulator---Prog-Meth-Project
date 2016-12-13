package algorithm;

import java.util.HashSet;

import javafx.scene.media.AudioClip;
import logic.Edge;
import logic.GraphData;
import logic.Vertex;
import utility.ResourceLoader;

public class ConnectedComponent extends Algorithm {

	private static ConnectedComponent instance = new ConnectedComponent();
	private Vertex source;
	private HashSet<Vertex> visited = new HashSet<>();

	public static ConnectedComponent getInstance() {
		return instance;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	private void search(Vertex vertex) {
		visited.add(vertex);
		vertex.setIsSelected(true);
		for (Edge edge : vertex.getEdges()) {
			Vertex adjacentVertex;
			if (edge.getFrom() == vertex) {
				adjacentVertex = edge.getTo();
			} else {
				adjacentVertex = edge.getFrom();
			}
			if (!visited.contains(adjacentVertex)) {
				search(adjacentVertex);
			}
		}
	}

	public void solve() {
		visited.clear();
		for (Vertex vertex : GraphData.getInstance().getVertices().values()) {
			vertex.setIsSelected(false);
		}
		search(source);
		for (Edge edge : GraphData.getInstance().getEdges().values()) {
			edge.setIsSelected(edge.getFrom().isSelected() && edge.getTo().isSelected());
		}
		AudioClip audioClip = ResourceLoader.loadAudioClip("ding.mp3");
		audioClip.play();
	}

}
