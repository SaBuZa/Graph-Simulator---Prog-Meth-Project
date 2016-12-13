package thread;

import javafx.application.Platform;
import logic.Entity;

public class EntityRunnable implements Runnable {

	private Entity entity;

	public EntityRunnable(Entity entity) {
		this.entity = entity;
	}

	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				entity.initGUI();
			}
		});
		while (true) {
			Platform.runLater(new Runnable() {
				public void run() {
					entity.updateGUI();
				}
			});
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Platform.runLater(new Runnable() {
					public void run() {
						entity.removeGUI();
					}
				});
				return;
			}
		}
	}

}
