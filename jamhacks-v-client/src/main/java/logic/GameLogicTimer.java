package logic;

public class GameLogicTimer extends TimestepTimer {

	private GameLogic logic;
	private boolean isDone = false;

	public GameLogicTimer(GameLogic logic, TimeAccumulator accumulator) {
		super(10, accumulator);
		this.logic = logic;
	}

	@Override
	protected void doUpdate() {
		logic.update();
	}

	public void end() {
		isDone = true;
	}

	@Override
	protected boolean endCondition() {
		return isDone;
	}

}