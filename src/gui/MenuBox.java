package gui;

import command.algorithm_command.BipartiteColoringCommand;
import command.algorithm_command.ConnectedComponentCommand;
import command.algorithm_command.MinimumSpanningTreeCommand;
import command.edit_command.AddEdgeCommand;
import command.edit_command.AddVertexCommand;
import command.edit_command.DeleteEdgeCommand;
import command.edit_command.DeleteVertexCommand;
import command.edit_command.EditEdgeCommand;
import command.edit_command.EditVertexCommand;
import command.file_command.ExitCommand;
import command.file_command.LoadCommand;
import command.file_command.NewCommand;
import command.file_command.SaveCommand;
import command.file_command.SettingCommand;
import command.help_command.AboutUsCommand;
import command.help_command.HowToUseCommand;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBox extends MenuBar {

	public MenuBox() {

		// File Menu
		Menu fileMenu = new Menu("File");
		MenuBoxItem newItem = new MenuBoxItem("New", NewCommand.getInstance());
		MenuBoxItem loadItem = new MenuBoxItem("Load", LoadCommand.getInstance());
		MenuBoxItem saveItem = new MenuBoxItem("Save", SaveCommand.getInstance());
		MenuBoxItem settingItem = new MenuBoxItem("Setting", SettingCommand.getInstance());
		MenuBoxItem exitItem = new MenuBoxItem("Exit", ExitCommand.getInstance());
		fileMenu.getItems().addAll(newItem, loadItem, saveItem, settingItem, exitItem);

		// Edit Menu
		Menu editMenu = new Menu("Edit");
		MenuBoxItem addVertexItem = new MenuBoxItem("Add Vertex", AddVertexCommand.getInstance());
		MenuBoxItem addEdgeItem = new MenuBoxItem("Add Edge", AddEdgeCommand.getInstance());
		MenuBoxItem editVertexItem = new MenuBoxItem("Edit Vertex", EditVertexCommand.getInstance());
		MenuBoxItem editEdgeItem = new MenuBoxItem("Edit Edge", EditEdgeCommand.getInstance());
		MenuBoxItem deleteVertexItem = new MenuBoxItem("Delete Vertex", DeleteVertexCommand.getInstance());
		MenuBoxItem deleteEdgeItem = new MenuBoxItem("Delete Edge", DeleteEdgeCommand.getInstance());
		editMenu.getItems().addAll(addVertexItem, addEdgeItem, editVertexItem, editEdgeItem, deleteVertexItem,
				deleteEdgeItem);

		// Algorithm Menu
		Menu algorithmMenu = new Menu("Algorithm");
		MenuBoxItem bipartiteColoringItem = new MenuBoxItem("Bipartite Coloring",
				BipartiteColoringCommand.getInstance());
		MenuBoxItem connectedComponentItem = new MenuBoxItem("Connected Component",
				ConnectedComponentCommand.getInstance());
		MenuBoxItem minimumSpanningTreeItem = new MenuBoxItem("Minimum Spanning Tree",
				MinimumSpanningTreeCommand.getInstance());
		algorithmMenu.getItems().addAll(bipartiteColoringItem, connectedComponentItem, minimumSpanningTreeItem);

		// Help Menu
		Menu helpMenu = new Menu("Help");
		MenuBoxItem howToUseItem = new MenuBoxItem("How To Use", HowToUseCommand.getInstance());
		MenuBoxItem aboutUsItem = new MenuBoxItem("About Us", AboutUsCommand.getInstance());
		helpMenu.getItems().addAll(howToUseItem, aboutUsItem);

		// Add Menus to MenuBar
		this.getMenus().addAll(fileMenu, editMenu, algorithmMenu, helpMenu);

	}

}
