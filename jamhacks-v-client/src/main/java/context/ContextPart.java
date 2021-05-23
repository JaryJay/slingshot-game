package context;

import processing.core.PApplet;

public abstract class ContextPart {

	private GameContext context;

	public void init(PApplet p) {
	}

	public GameContext getContext() {
		return context;
	}

	public void setContext(GameContext context) {
		this.context = context;
	}

}
