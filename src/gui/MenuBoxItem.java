package gui;

import command.Command;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuBoxItem extends MenuItem {

	public MenuBoxItem(String name, Command command) {
		super(name);
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				command.use();
			}
		});
	}

}
