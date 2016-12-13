package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EdgeNotFoundException extends Exception {
	
	public void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText("Edge Not Found");
		alert.showAndWait();
	}

}
