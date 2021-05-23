package context;

import processing.core.PApplet;

public class GameContextWrapper {

	private GameContext gameContext;
	private PApplet gameSketch;

	public GameContextWrapper(GameContext gameContext) {
		this.gameContext = gameContext;
		gameContext.setWrapper(this);
	}

	public void init(PApplet p) {
		gameContext.init(p);
	}

	public void transition(GameContext gameContext) {
		this.gameContext = gameContext;
		gameContext.setWrapper(this);
		gameContext.init(gameSketch);
	}

	public GameContext getContext() {
		return gameContext;
	}

	public void setPApplet(PApplet gameSketch) {
		this.gameSketch = gameSketch;
	}

}
