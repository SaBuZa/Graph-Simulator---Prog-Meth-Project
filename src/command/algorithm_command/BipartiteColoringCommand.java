package command.algorithm_command;

import algorithm.BipartiteColoring;
import command.ConfirmativeCommand;
import gui.GUISetting;

public class BipartiteColoringCommand extends ConfirmativeCommand {

	private static BipartiteColoringCommand instance = new BipartiteColoringCommand();

	public static BipartiteColoringCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.BIPARTITE_COLORING_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Bipartite Coloring";
	}

	protected String getContentText() {
		return "This will change nodes and edges Selected property. Proceed?";
	}

	protected void operate() {
		BipartiteColoring.getInstance().solve();
	}

}
