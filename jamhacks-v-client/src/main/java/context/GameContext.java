package context;

import data.GameData;
import logic.GameLogic;
import processing.core.PApplet;
import visuals.GameVisuals;

public class GameContext {

	private GameLogic logic;
	private GameVisuals visuals;
	private GameData data;
	private GameContextWrapper wrapper;

	public GameContext(GameLogic logic, GameVisuals visuals, GameData data) {
		this.logic = logic;
		this.visuals = visuals;
		this.data = data;
		logic.setContext(this);
		visuals.setContext(this);
		data.setContext(this);
	}

	public void init(PApplet p) {
		logic.init(p);
		visuals.init(p);
		data.init(p);
	}

	public GameLogic getLogic() {
		return logic;
	}

	public GameVisuals getVisuals() {
		return visuals;
	}

	public GameData getData() {
		return data;
	}

	public void setWrapper(GameContextWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public GameContextWrapper getWrapper() {
		return wrapper;
	}

}
