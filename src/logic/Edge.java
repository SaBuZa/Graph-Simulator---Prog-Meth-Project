package logic;

import com.sun.javafx.tk.Toolkit;

import exception.DeserializeFailException;
import gui.SimulatorScreen;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import utility.ColorManager;

public class Edge extends Entity implements ISerializable, Comparable<Edge> {
	private Vertex from, to;
	private double cost;

	private Line line;
	private Label label;
	private double labelWidth;
	private double labelHeight;

	public Edge() {
		super();
		from = new Vertex();
		to = new Vertex();
		cost = 0;
	}

	public Edge(Vertex from, Vertex to, double cost, boolean isSelected) {
		super(isSelected);
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	public Vertex getFrom() {
		return from;
	}

	public Vertex getTo() {
		return to;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void sortVertices() {
		if (from.getName().compareTo(to.getName()) > 0) {
			Vertex temp = from;
			from = to;
			to = temp;
		}
	}

	public void initGUI() {
		line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
		label = new Label(Double.toString(cost));
		label.setMouseTransparent(true);
		label.setStyle("-fx-background-radius : 2; -fx-background-color: #FFFFFF; -fx-label-padding: 2;");
		SimulatorScreen.getInstance().addEdge(line);
		SimulatorScreen.getInstance().addEdgeLabel(label);
	}

	public void updateGUI() {
		line.setStartX(from.getX());
		line.setStartY(from.getY());
		line.setEndX(to.getX());
		line.setEndY(to.getY());
		line.setTranslateX(Math.min(from.getX(), to.getX()));
		line.setTranslateY(Math.min(from.getY(), to.getY()));
		line.setStroke(ColorManager.getEdgeColor(isSelected));
		Font font = new Font("Arial", SettingData.getInstance().getEdgeFontSize());
		labelWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(Double.toString(cost), font);
		labelHeight = Toolkit.getToolkit().getFontLoader().getFontMetrics(font).getLineHeight();
		label.setFont(font);
		label.setTextFill(ColorManager.getEdgeLabelColor(isSelected));
		label.setTranslateX(from.getX() / 2 + to.getX() / 2 - labelWidth / 2);
		label.setTranslateY(from.getY() / 2 + to.getY() / 2 - labelHeight / 2);
		label.setText(Double.toString(cost));
		label.setVisible(SettingData.getInstance().isShowEdgeCost());
	}

	public void removeGUI() {
		SimulatorScreen.getInstance().removeEdge(line);
		SimulatorScreen.getInstance().removeEdgeLabel(label);
	}

	@Override
	public String serialize() {
		return from.getName() + " " + to.getName() + " " + Double.toString(cost) + " " + Boolean.toString(isSelected)
				+ "\n";
	}

	@Override
	public void deserialize(String serializedData) throws DeserializeFailException {
		try {
			String[] data = serializedData.trim().split(" +");
			from = GraphData.getInstance().getVertex(data[0]);
			to = GraphData.getInstance().getVertex(data[1]);
			cost = Double.parseDouble(data[2]);
			isSelected = Boolean.parseBoolean(data[3]);
		} catch (Exception e) {
			throw new DeserializeFailException();
		}
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(cost, o.cost);
	}
}
