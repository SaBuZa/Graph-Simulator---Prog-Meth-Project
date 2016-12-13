package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VertexNotFoundException extends Exception {

	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText("Vertex Not Found");
		alert.showAndWait();
	}

}
