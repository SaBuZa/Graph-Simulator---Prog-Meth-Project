package gui;

import command.edit_command.AddEdgeCommand;
import command.edit_command.AddVertexCommand;
import command.edit_command.DeleteEdgeCommand;
import command.edit_command.DeleteVertexCommand;
import command.edit_command.EditEdgeCommand;
import command.edit_command.EditVertexCommand;
import command.file_command.SettingCommand;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ToolBox extends GridPane {

	public ToolBox() {

		this.add(new ToolButton(AddVertexCommand.getInstance()), 0, 0);
		this.add(new ToolButton(AddEdgeCommand.getInstance()), 0, 1);
		this.add(new ToolButton(EditVertexCommand.getInstance()), 0, 2);
		this.add(new ToolButton(EditEdgeCommand.getInstance()), 0, 3);
		this.add(new ToolButton(DeleteVertexCommand.getInstance()), 0, 4);
		this.add(new ToolButton(DeleteEdgeCommand.getInstance()), 0, 5);
		this.add(new ToolButton(SettingCommand.getInstance()), 0, 6);

		this.setPrefWidth(2 * GUISetting.PADDING_SIZE + GUISetting.PICTURE_WIDTH);
		this.setPadding(new Insets(GUISetting.PADDING_SIZE));
		this.setHgap(GUISetting.GAP_SIZE);
		this.setVgap(GUISetting.GAP_SIZE);
	}

}
