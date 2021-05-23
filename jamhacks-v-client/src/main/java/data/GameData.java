package data;

import context.ContextPart;

public class GameData extends ContextPart {

	private int currentScreen = 1;

	public int getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(int currentScreen) {
		this.currentScreen = currentScreen;
	}

}
