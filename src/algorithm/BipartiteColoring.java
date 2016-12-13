package algorithm;

import java.util.HashSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import logic.Edge;
import logic.GraphData;
import logic.Vertex;
import utility.ResourceLoader;

public class BipartiteColoring extends Algorithm {

	private static BipartiteColoring instance = new BipartiteColoring();
	private boolean fail;
	private HashSet<Vertex> group1 = new HashSet<>();
	private HashSet<Vertex> group2 = new HashSet<>();

	public static BipartiteColoring getInstance() {
		return instance;
	}

	private void search(Vertex vertex, int group) {
		if (group == 1) {
			group1.add(vertex);
			for (Edge edge : vertex.getEdges()) {
				Vertex adjacentVertex;
				if (edge.getFrom() == vertex) {
					adjacentVertex = edge.getTo();
				} else {
					adjacentVertex = edge.getFrom();
				}
				if (group1.contains(adjacentVertex)) {
					fail = true;
				} else {
					if (!group2.contains(adjacentVertex)) {
						search(adjacentVertex, 2);
					}
				}
			}
		} else {
			group2.add(vertex);
			for (Edge edge : vertex.getEdges()) {
				Vertex adjacentVertex;
				if (edge.getFrom() == vertex) {
					adjacentVertex = edge.getTo();
				} else {
					adjacentVertex = edge.getFrom();
				}
				if (group2.contains(adjacentVertex)) {
					fail = true;
				} else {
					if (!group1.contains(adjacentVertex)) {
						search(adjacentVertex, 1);
					}
				}
			}
		}
	}

	public void solve() {
		fail = false;
		group1.clear();
		group2.clear();
		for (Vertex vertex : GraphData.getInstance().getVertices().values()) {
			if (!group1.contains(vertex) && !group2.contains(vertex)) {
				search(vertex, 1);
			}
		}
		if (fail) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Bipartite Coloring");
			alert.setHeaderText(null);
			alert.setContentText("Graph is not a Bipartite Graph.");
			alert.showAndWait();
		} else {
			for (Vertex vertex : group1) {
				vertex.setIsSelected(false);
			}
			for (Vertex vertex : group2) {
				vertex.setIsSelected(true);
			}
			for (Edge edge : GraphData.getInstance().getEdges().values()) {
				edge.setIsSelected(true);
			}
			AudioClip audioClip = ResourceLoader.loadAudioClip("ding.mp3");
			audioClip.play();
		}
	}

}
