package command;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public abstract class ConfirmativeCommand extends Command {

	protected abstract String getTitle();

	protected abstract String getContentText();

	protected abstract void operate();

	public void use() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(getTitle());
		alert.setHeaderText(null);
		alert.setContentText(getContentText());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			operate();
		}
	}

}
