package context;

import processing.core.PApplet;

public class GameContextWrapper {

	private GameContext gameContext;
	private PApplet gameSketch;

	public GameContextWrapper(GameContext gameContext) {
		gameContext.setWrapper(this);
		this.gameContext = gameContext;
	}

	public void init(PApplet p) {
		gameContext.init(p);
	}

	public void transition(GameContext gameContext) {
		gameContext.setWrapper(this);
		gameContext.init(gameSketch);
		this.gameContext = gameContext;
	}

	public GameContext getContext() {
		return gameContext;
	}

	public void setPApplet(PApplet gameSketch) {
		this.gameSketch = gameSketch;
	}

}
