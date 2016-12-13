package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidEdgeException extends Exception {

	private String information;

	public InvalidEdgeException(int type) {
		if (type == 1) {
			information = "Edge Cannot Be Self-Loop";
		}
		else {
			information = "Edge Between Vertices Already Exist";
		}
	};

	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText(information);
		alert.showAndWait();
	}

}
