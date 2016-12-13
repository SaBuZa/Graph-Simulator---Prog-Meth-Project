package utility;

import javafx.scene.paint.Color;

public class ColorManager {

	public static Color getVertexColor(boolean isSelected) {
		if (isSelected) {
			return Color.ORANGE;
		} else {
			return Color.ROYALBLUE;
		}
	}

	public static Color getVertexLabelColor(boolean isSelected) {
		if (isSelected) {
			return Color.PURPLE;
		} else {
			return Color.LIGHTGOLDENRODYELLOW;
		}
	}

	public static Color getEdgeColor(boolean isSelected) {
		if (isSelected) {
			return Color.BROWN;
		} else {
			return Color.LIGHTSEAGREEN;
		}
	}

	public static Color getEdgeLabelColor(boolean isSelected) {
		if (isSelected) {
			return Color.BROWN;
		} else {
			return Color.LIGHTSEAGREEN;
		}
	}

}
