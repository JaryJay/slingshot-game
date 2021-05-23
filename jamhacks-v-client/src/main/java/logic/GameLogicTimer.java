package logic;

import context.GameContextWrapper;

public class GameLogicTimer extends TimestepTimer {

	private GameContextWrapper wrapper;
	private boolean isDone = false;

	public GameLogicTimer(GameContextWrapper wrapper, TimeAccumulator accumulator) {
		super(10, accumulator);
		this.wrapper = wrapper;
	}

	@Override
	protected void doUpdate() {
		wrapper.getContext().getLogic().update();
	}

	public void end() {
		isDone = true;
	}

	@Override
	protected boolean endCondition() {
		return isDone;
	}

}