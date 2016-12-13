package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidVertexException extends Exception {

	private String information;

	public String getInformation() {
		return information;
	}

	public InvalidVertexException(int type) {
		if (type == 1) {
			information = "Vertex Name Already Exist";
		}
	}

	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText(information);
		alert.showAndWait();
	}

}
