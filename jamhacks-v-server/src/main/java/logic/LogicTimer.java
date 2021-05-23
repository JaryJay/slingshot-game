package logic;

import java.util.Queue;

import state.ImmutableGameState;
import timer.TimeAccumulator;
import timer.TimestepTimer;
import util.LimitedQueue;

public class LogicTimer extends TimestepTimer {

	Queue<ImmutableGameState> states = new LimitedQueue<>(100);
	private boolean isDone = false;

	public LogicTimer(TimeAccumulator accumulator) {
		super(10, accumulator);
	}

	@Override
	protected void doUpdate() {

	}

	public void end() {
		isDone = true;
	}

	@Override
	protected boolean endCondition() {
		return isDone;
	}

}