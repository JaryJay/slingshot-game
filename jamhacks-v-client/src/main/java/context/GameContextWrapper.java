package context;

public class GameContextWrapper {

	private GameContext gameContext;

	public GameContextWrapper(GameContext gameContext) {
		this.gameContext = gameContext;
	}

	public void transition(GameContext gameContext) {
		this.gameContext = gameContext;
	}

	public GameContext getContext() {
		return gameContext;
	}

}
