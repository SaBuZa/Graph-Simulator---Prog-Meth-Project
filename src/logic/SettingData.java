package logic;

import exception.DeserializeFailException;

public class SettingData implements ISerializable {
	public static final int VERTEX_RADIUS_DEFAULT = 30;
	public static final int VERTEX_RADIUS_MIN = 10;
	public static final int VERTEX_RADIUS_MAX = 100;

	public static final int VERTEX_FONT_SIZE_DEFAULT = 24;
	public static final int VERTEX_FONT_SIZE_MIN = 8;
	public static final int VERTEX_FONT_SIZE_MAX = 80;

	public static final int EDGE_FONT_SIZE_DEFAULT = 18;
	public static final int EDGE_FONT_SIZE_MIN = 6;
	public static final int EDGE_FONT_SIZE_MAX = 60;

	public static final boolean IS_SHOW_EDGE_COST_DEFAULT = true;

	private int vertexRadius = VERTEX_RADIUS_DEFAULT;
	private int vertexFontSize = VERTEX_FONT_SIZE_DEFAULT;
	private int edgeFontSize = EDGE_FONT_SIZE_DEFAULT;
	private boolean isShowEdgeCost = IS_SHOW_EDGE_COST_DEFAULT;

	private static SettingData instance = new SettingData();

	public static SettingData getInstance() {
		return instance;
	}

	public int getVertexRadius() {
		return vertexRadius;
	}

	public void setVertexRadius(int vertexRadius) {
		this.vertexRadius = vertexRadius;
	}

	public int getVertexFontSize() {
		return vertexFontSize;
	}

	public void setVertexFontSize(int vertexFontSize) {
		this.vertexFontSize = vertexFontSize;
	}

	public int getEdgeFontSize() {
		return edgeFontSize;
	}

	public void setEdgeFontSize(int edgeFontSize) {
		this.edgeFontSize = edgeFontSize;
	}

	public boolean isShowEdgeCost() {
		return isShowEdgeCost;
	}

	public void setShowEdgeCost(boolean isShowEdgeCost) {
		this.isShowEdgeCost = isShowEdgeCost;
	}

	@Override
	public String serialize() {
		return Integer.toString(vertexRadius) + " " + Integer.toString(vertexFontSize) + " "
				+ Integer.toString(edgeFontSize) + " " + Boolean.toString(isShowEdgeCost) + "\n";
	}

	@Override
	public void deserialize(String serializedData) throws DeserializeFailException {
		try {
			String[] data = serializedData.trim().split(" +");
			setVertexRadius(Integer.parseInt(data[0]));
			setVertexFontSize(Integer.parseInt(data[1]));
			setEdgeFontSize(Integer.parseInt(data[2]));
			setShowEdgeCost(Boolean.parseBoolean(data[3]));
		} catch (Exception e) {
			throw new DeserializeFailException();
		}
	}

}
