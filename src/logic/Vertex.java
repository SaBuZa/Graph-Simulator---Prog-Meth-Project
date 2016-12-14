package logic;

import java.util.HashSet;

import com.sun.javafx.tk.Toolkit;

import exception.DeserializeFailException;
import gui.SimulatorScreen;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import utility.ColorManager;

public class Vertex extends Entity implements ISerializable {
	private String name;
	private double x, y;

	private Circle circle;
	private Label label;

	private double clickX, clickY;
	private double mouseX, mouseY;
	private double oldX, oldY;

	private HashSet<Edge> edges;

	public Vertex() {
		super();
		this.name = "null";
		this.x = 0;
		this.y = 0;
		this.edges = new HashSet<Edge>();
	}

	public Vertex(String name, boolean isSelected) {
		super(isSelected);
		this.name = name;
		this.x = SimulatorScreen.getInstance().getWidth() / 2 - SettingData.getInstance().getVertexRadius();
		this.y = SimulatorScreen.getInstance().getHeight() / 2 - SettingData.getInstance().getVertexRadius();
		this.edges = new HashSet<Edge>();
	}

	public Vertex(String name, boolean isSelected, double x, double y) {
		super(isSelected);
		this.name = name;
		this.x = x;
		this.y = y;
		this.edges = new HashSet<Edge>();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}

	public void fixOverflow() {
		if (x < SettingData.getInstance().getVertexRadius()) {
			x = SettingData.getInstance().getVertexRadius();
		}
		if (y < SettingData.getInstance().getVertexRadius()) {
			y = SettingData.getInstance().getVertexRadius();
		}
		if (x > SimulatorScreen.getInstance().getWidth() - SettingData.getInstance().getVertexRadius()) {
			x = SimulatorScreen.getInstance().getWidth() - SettingData.getInstance().getVertexRadius();
		}
		if (y > SimulatorScreen.getInstance().getHeight() - SettingData.getInstance().getVertexRadius()) {
			y = SimulatorScreen.getInstance().getHeight() - SettingData.getInstance().getVertexRadius();
		}
	}

	public void initGUI() {
		circle = new Circle(SettingData.getInstance().getVertexRadius(), ColorManager.getVertexColor(isSelected));
		circle.setCursor(Cursor.HAND);
		circle.setTranslateX(x - SettingData.getInstance().getVertexRadius());
		circle.setTranslateY(y - SettingData.getInstance().getVertexRadius());
		circle.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				clickX = event.getSceneX();
				clickY = event.getSceneY();
				oldX = x;
				oldY = y;
			}
		});
		circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Circle circle = (Circle) (event.getSource());
				mouseX = event.getSceneX();
				mouseY = event.getSceneY();
				x = oldX + mouseX - clickX;
				y = oldY + mouseY - clickY;
				fixOverflow();
				circle.setTranslateX(x - SettingData.getInstance().getVertexRadius());
				circle.setTranslateY(y - SettingData.getInstance().getVertexRadius());
			}
		});
		circle.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getSceneX();
				mouseY = event.getSceneY();
			}
		});

		label = new Label(name);
		label.setMouseTransparent(true);

		SimulatorScreen.getInstance().addVertex(circle);
		SimulatorScreen.getInstance().addVertexLabel(label);
	}

	public void updateGUI() {
		circle.setRadius(SettingData.getInstance().getVertexRadius());
		circle.setFill(ColorManager.getVertexColor(isSelected));
		Font font = new Font("Arial", SettingData.getInstance().getVertexFontSize());
		double labelWidth = Toolkit.getToolkit().getFontLoader().computeStringWidth(name, font);
		double labelHeight = Toolkit.getToolkit().getFontLoader().getFontMetrics(font).getLineHeight();
		label.setFont(font);
		label.setTextFill(ColorManager.getVertexLabelColor(isSelected));
		fixOverflow();
		circle.setTranslateX(x - SettingData.getInstance().getVertexRadius());
		circle.setTranslateY(y - SettingData.getInstance().getVertexRadius());
		label.setTranslateX(x - labelWidth / 2);
		label.setTranslateY(y - labelHeight / 2);
		label.setText(name);
	}

	public void removeGUI() {
		SimulatorScreen.getInstance().removeVertex(circle);
		SimulatorScreen.getInstance().removeVertexLabel(label);
	}

	@Override
	public String serialize() {
		return name + " " + Boolean.toString(isSelected) + " " + Double.toString(x) + " " + Double.toString(y) + "\n";
	}

	@Override
	public void deserialize(String serializedData) throws DeserializeFailException {
		try {
			String[] data = serializedData.trim().split(" +");
			this.setName(data[0]);
			this.setIsSelected(Boolean.parseBoolean(data[1]));
			this.setX(Double.parseDouble(data[2]));
			this.setY(Double.parseDouble(data[3]));
		} catch (Exception e) {
			throw new DeserializeFailException();
		}
	}

}
