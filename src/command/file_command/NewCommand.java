package command.file_command;

import command.ConfirmativeCommand;
import gui.GUISetting;
import logic.GraphData;

public class NewCommand extends ConfirmativeCommand {

	private static NewCommand instance = new NewCommand();

	public static NewCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.NEW_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Create New Simulation";
	}

	protected String getContentText() {
		return "Unsaved data will be lost.\nAre you sure?";
	}

	protected void operate() {
		GraphData.getInstance().clear();
	}

}
