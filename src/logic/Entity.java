package logic;

import thread.EntityRunnable;
import thread.ThreadHolder;

public abstract class Entity {

	protected boolean isSelected;
	protected Thread thread;

	public Entity() {
		this.isSelected = false;
	}

	public Entity(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Thread getThread() {
		return thread;
	}

	public void draw() {
		thread = new Thread(new EntityRunnable(this));
		ThreadHolder.addThread(thread);
		thread.start();
	}

	public void destroy() {
		ThreadHolder.removeThread(thread);
		thread.interrupt();
	}

	public abstract void initGUI();

	public abstract void updateGUI();

	public abstract void removeGUI();

}
