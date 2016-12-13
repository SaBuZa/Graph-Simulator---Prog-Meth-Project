package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import exception.DeserializeFailException;
import exception.EdgeNotFoundException;
import exception.InvalidEdgeException;
import exception.InvalidVertexException;
import exception.VertexNotFoundException;
import gui.SimulatorScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import thread.ThreadHolder;

public class GraphData implements ISerializable {

	private static GraphData instance = new GraphData();
	private HashMap<String, Vertex> vertices;
	private HashMap<Pair<Vertex, Vertex>, Edge> edges;

	public GraphData() {
		vertices = new HashMap<>();
		edges = new HashMap<>();
	}

	public static GraphData getInstance() {
		return instance;
	}

	public static void save(File file) throws IOException {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(instance.serialize());
			bufferedWriter.close();
		} catch (IOException e) {
			throw e;
		}
	}

	public static void load(File file) throws IOException, DeserializeFailException {
		BufferedReader bufferedReader = null;
		String data = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String text = null;
			while ((text = bufferedReader.readLine()) != null) {
				data += text + "\n";
			}
			bufferedReader.close();
			instance.deserialize(data);
		} catch (DeserializeFailException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public HashMap<String, Vertex> getVertices() {
		return vertices;
	}

	public HashMap<Pair<Vertex, Vertex>, Edge> getEdges() {
		return edges;
	}

	public void clear() {
		vertices.clear();
		edges.clear();
		ThreadHolder.interruptAllThreads();
		SimulatorScreen.getInstance().clear();
	}

	public Vertex getVertex(String name) throws VertexNotFoundException {
		if (vertices.containsKey(name)) {
			return vertices.get(name);
		} else {
			throw new VertexNotFoundException();
		}
	}

	public Edge getEdge(Vertex from, Vertex to) throws EdgeNotFoundException {
		Pair<Vertex, Vertex> pair1 = new Pair<>(from, to);
		Pair<Vertex, Vertex> pair2 = new Pair<>(to, from);
		if (edges.containsKey(pair1)) {
			return edges.get(pair1);
		} else if (edges.containsKey(pair2)) {
			return edges.get(pair2);
		} else {
			throw new EdgeNotFoundException();
		}
	}

	public void addVertex(Vertex vertex) throws InvalidVertexException {
		if (vertices.containsKey(vertex.getName())) {
			throw new InvalidVertexException();
		} else {
			vertices.put(vertex.getName(), vertex);
			vertex.draw();
		}
	}

	public void addEdge(Edge edge) throws InvalidEdgeException {
		edge.sortVertices();
		Pair<Vertex, Vertex> pair = new Pair<>(edge.getFrom(), edge.getTo());
		if (edge.getFrom() == edge.getTo()) {
			throw new InvalidEdgeException(1);
		} else if (edges.containsKey(pair)) {
			throw new InvalidEdgeException(2);
		} else {
			edges.put(pair, edge);
			edge.getFrom().addEdge(edge);
			edge.getTo().addEdge(edge);
			edge.draw();
		}
	}

	public void deleteVertex(String name) throws VertexNotFoundException, EdgeNotFoundException {
		try {
			Vertex vertex = getVertex(name);
			for (Edge edge : vertex.getEdges()) {
				deleteEdge(edge.getFrom(), edge.getTo());
			}
			vertices.remove(name);
			vertex.destroy();
		} catch (VertexNotFoundException e) {
			throw e;
		} catch (EdgeNotFoundException e) {
			throw e;
		}
	}

	public void deleteEdge(Vertex vertex1, Vertex vertex2) throws EdgeNotFoundException {
		try {
			Edge edge = getEdge(vertex1, vertex2);
			edges.remove(new Pair<>(edge.getFrom(), edge.getTo()));
			edge.destroy();
		} catch (EdgeNotFoundException e) {
			throw e;
		}
	}

	public void deleteEdge(String name1, String name2) throws EdgeNotFoundException, VertexNotFoundException {
		try {
			deleteEdge(getVertex(name1), getVertex(name2));
		} catch (VertexNotFoundException e) {
			throw e;
		} catch (EdgeNotFoundException e) {
			throw e;
		}
	}

	public void editVertex(String target, String name, boolean isSelected)
			throws VertexNotFoundException, InvalidVertexException {
		try {
			Vertex targetVertex = getVertex(target);
			if (vertices.containsKey(name)) {
				throw new InvalidVertexException();
			} else {
				vertices.remove(targetVertex.getName());
				targetVertex.setName(name);
				targetVertex.setIsSelected(isSelected);
				vertices.put(name, targetVertex);
			}
		} catch (VertexNotFoundException e) {
			throw e;
		}
	}

	public void editEdge(String from, String to, double cost, boolean isSelected)
			throws VertexNotFoundException, EdgeNotFoundException {
		try {
			Edge edge = getEdge(getVertex(from), getVertex(to));
			edge.setCost(cost);
			edge.setIsSelected(isSelected);
		} catch (VertexNotFoundException e) {
			throw e;
		} catch (EdgeNotFoundException e) {
			throw e;
		}
	}

	public ObservableList<String> getVerticesName() {
		return FXCollections.observableArrayList(vertices.keySet());
	}

	@Override
	public String serialize() {
		String serializedData = Integer.toString(vertices.size()) + " " + Integer.toString(edges.size()) + "\n";
		for (Vertex vertex : vertices.values()) {
			serializedData += vertex.serialize();
		}
		for (Edge edge : edges.values()) {
			serializedData += edge.serialize();
		}
		serializedData += SettingData.getInstance().serialize();
		return serializedData;
	}

	@Override
	public void deserialize(String serializedData) throws DeserializeFailException {
		try {
			this.clear();
			String[] data = serializedData.trim().split("\n+");
			String[] temp = data[0].trim().split(" +");
			int verticesCount = Integer.parseInt(temp[0]);
			int edgesCount = Integer.parseInt(temp[1]);
			for (int i = 1; i <= verticesCount; i++) {
				Vertex vertex = new Vertex();
				vertex.deserialize(data[i]);
				this.addVertex(vertex);
			}
			for (int i = verticesCount + 1; i <= verticesCount + edgesCount; i++) {
				Edge edge = new Edge();
				edge.deserialize(data[i]);
				this.addEdge(edge);
			}
			SettingData.getInstance().deserialize(data[verticesCount + edgesCount + 1]);
		} catch (Exception e) {
			this.clear();
			throw new DeserializeFailException();
		}
	}

}
