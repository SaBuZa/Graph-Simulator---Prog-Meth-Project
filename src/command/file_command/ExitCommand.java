package command.file_command;

import command.ConfirmativeCommand;
import gui.GUISetting;
import javafx.application.Platform;

public class ExitCommand extends ConfirmativeCommand {

	private static ExitCommand instance = new ExitCommand();

	public static ExitCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.EXIT_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Exit Application";
	}

	protected String getContentText() {
		return "Unsaved data will be lost.\nAre you sure?";
	}

	protected void operate() {
		Platform.exit();
	}

}
