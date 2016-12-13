package command.algorithm_command;

import algorithm.MinimumSpanningTree;
import command.ConfirmativeCommand;
import gui.GUISetting;

public class MinimumSpanningTreeCommand extends ConfirmativeCommand {

	private static MinimumSpanningTreeCommand instance = new MinimumSpanningTreeCommand();

	public static MinimumSpanningTreeCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.MINIMUM_SPANNING_TREE_IMAGE_PATH;
	}

	protected String getTitle() {
		return "Minimum Spanning Tree";
	}

	protected String getContentText() {
		return "This will change nodes and edges Selected property. Proceed?";
	}

	protected void operate() {
		MinimumSpanningTree.getInstance().solve();
	}

}
