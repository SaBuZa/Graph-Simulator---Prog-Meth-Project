package algorithm;

import java.util.Collections;
import java.util.HashMap;

import javafx.scene.media.AudioClip;

import java.util.ArrayList;

import logic.Edge;
import logic.GraphData;
import logic.Vertex;
import utility.ResourceLoader;

public class MinimumSpanningTree extends Algorithm {

	private static MinimumSpanningTree instance = new MinimumSpanningTree();
	private HashMap<Vertex, Vertex> parents = new HashMap<>();

	public static MinimumSpanningTree getInstance() {
		return instance;
	}

	private Vertex getParent(Vertex vertex) {
		if (vertex == parents.get(vertex)) {
			return vertex;
		} else {
			Vertex parent = getParent(parents.get(vertex));
			parents.put(vertex, parent);
			return parent;
		}
	}

	public void solve() {
		parents.clear();
		for (Vertex vertex : GraphData.getInstance().getVertices().values()) {
			vertex.setIsSelected(true);
			parents.put(vertex, vertex);
		}
		ArrayList<Edge> edges = new ArrayList<Edge>(GraphData.getInstance().getEdges().values());
		Collections.sort(edges);
		for (Edge edge : edges) {
			Vertex fromParent = getParent(edge.getFrom());
			Vertex toParent = getParent(edge.getTo());
			if (fromParent == toParent) {
				edge.setIsSelected(false);
			} else {
				parents.put(toParent, fromParent);
				edge.setIsSelected(true);
			}
		}
		AudioClip audioClip = ResourceLoader.loadAudioClip("ding.mp3");
		audioClip.play();
	}

}
