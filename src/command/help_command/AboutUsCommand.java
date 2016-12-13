package command.help_command;

import command.Command;
import gui.GUISetting;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AboutUsCommand extends Command {

	private static AboutUsCommand instance = new AboutUsCommand();

	public static AboutUsCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.ABOUT_US_IMAGE_PATH;
	}

	@Override
	public void use() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Us");
		alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.setContentText( "Thanapat Katapornsiri 5831025021\n"
							+ "Sarot Busala 5831075321\n\n"
							+ "Faculty of Engineering\n" 
							+ "Chulalongkorn University" );
		alert.showAndWait();
	}

}
