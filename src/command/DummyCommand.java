package command;

import gui.GUISetting;

public class DummyCommand extends Command {

	private static DummyCommand instance = new DummyCommand();

	public static DummyCommand getInstance() {
		return instance;
	}

	public String getImagePath() {
		return GUISetting.DUMMY_IMAGE_PATH;
	}

	@Override
	public void use() {
		System.out.println("DummyCommand Activated!!!");
	}

}
