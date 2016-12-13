package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeserializeFailException extends Exception {

	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText("Invalid Graph Data");
		alert.showAndWait();
	}

}
