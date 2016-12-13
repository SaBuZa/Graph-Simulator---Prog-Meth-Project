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
	private HashMap<Vertex, Vertex> ancestors = new HashMap<>();

	public static MinimumSpanningTree getInstance() {
		return instance;
	}

	private Vertex getRoot(Vertex vertex) {
		if (vertex == ancestors.get(vertex)) {
			return vertex;
		} else {
			Vertex ancestor = getRoot(ancestors.get(vertex));
			ancestors.put(vertex, ancestor);
			return ancestor;
		}
	}

	public void solve() {
		ancestors.clear();
		for (Vertex vertex : GraphData.getInstance().getVertices().values()) {
			vertex.setIsSelected(true);
			ancestors.put(vertex, vertex);
		}
		ArrayList<Edge> edges = new ArrayList<Edge>(GraphData.getInstance().getEdges().values());
		Collections.sort(edges);
		for (Edge edge : edges) {
			Vertex fromRoot = getRoot(edge.getFrom());
			Vertex toRoot = getRoot(edge.getTo());
			if (fromRoot == toRoot) {
				edge.setIsSelected(false);
			} else {
				ancestors.put(toRoot, fromRoot);
				edge.setIsSelected(true);
			}
		}
		AudioClip audioClip = ResourceLoader.loadAudioClip("ding.mp3");
		audioClip.play();
	}

}
