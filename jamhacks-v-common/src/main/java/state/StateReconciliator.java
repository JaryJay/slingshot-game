package state;

import java.util.ArrayList;

import event.input.GameInputFrame;
import util.LimitedQueue;

public class StateReconciliator {

	private volatile LimitedQueue<GameState> states;
	private LimitedQueue<GameInputFrame> inputFrames;
	private int earliestDirtyFrame = 0;

	public StateReconciliator(LimitedQueue<GameState> states, LimitedQueue<GameInputFrame> inputFrames) {
		this.states = states;
		this.inputFrames = inputFrames;
	}

	public void reconcile() {
		for (int i = earliestDirtyFrame; i < states.size() - 1; i++) {
			GameState state = states.get(i);
			System.out.println("Resimulating state " + state.getId() + "");
			states.set(i + 1, GameStateExtrapolator.getNextState(state, inputFrames.get(i)));
		}
	}

	public void simulateNextState() {
		states.add(GameStateExtrapolator.getNextState(states.getLast(), inputFrames.getLast()));
		inputFrames.add(new GameInputFrame(inputFrames.getLast().getFrame() + 1));
		earliestDirtyFrame = states.size() - 1;
	}

	public void reloadInputFrame(GameInputFrame inputFrame) {
		long eventFrameNumber = inputFrame.getFrame();

		long earliestRememberedFrame = inputFrames.getFirst().getFrame();
		if (eventFrameNumber >= earliestRememberedFrame) {
			System.out.println("Reloading input frame " + eventFrameNumber);
			int index = (int) (eventFrameNumber - earliestRememberedFrame);
			inputFrames.get(index).getEvents().addAll(inputFrame.getEvents());
			earliestRememberedFrame = Math.min(index, earliestRememberedFrame);
		} else {
			System.out.println("Input frame discarded, too old");
			for (GameInputFrame event : inputFrames) {
				System.out.println(event.getEvents());
			}
		}
		ArrayList<GameInputFrame> list = new ArrayList<>();
		list.addAll(inputFrames);
	}

}
