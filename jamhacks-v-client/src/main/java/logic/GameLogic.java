package logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import event.inputfactory.VelocityChangeEvent;
import state.MutableGameState;

public class GameLogic {
	private GameData data;
	private Queue<AbstractGameInputEvent> inputBuffer;

	public GameLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer) {
		this.data = data;
		this.inputBuffer = inputBuffer;
	}

	public void update() {
		MutableGameState currentState = data.getCurrentState();
		data.getPastStates().add(currentState.immutable());
		data.setCurrentState(currentState.getNextState());
	}

	private ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedEvent) {
			VelocityChangeEvent velocityChangeEvent = null;
			KeyPressedEvent keyPressedEvent = (KeyPressedEvent) inputEvent;
			switch (keyPressedEvent.getKeyCode()) {
			case KeyEvent.VK_W:
				velocityChangeEvent = new VelocityChangeEvent(0f, -5f);
				break;
			case KeyEvent.VK_S:
				velocityChangeEvent = new VelocityChangeEvent(0f, 5f);
				break;
			case KeyEvent.VK_A:
				velocityChangeEvent = new VelocityChangeEvent(-5f, 0f);
				break;
			case KeyEvent.VK_D:
				velocityChangeEvent = new VelocityChangeEvent(0f, -5f);
				break;
			}
		} else if (inputEvent instanceof MousePressedGameInputEvent) {

		} else if (inputEvent instanceof MouseReleasedGameInputEvent) {

		}

		return null;
	}

	private List<ClientToServerGameEvent> handleAll() {
		List<ClientToServerGameEvent> eventList = new ArrayList<>();

		while (!inputBuffer.isEmpty()) {
			AbstractGameInputEvent poll = inputBuffer.poll();
			ClientToServerGameEvent returnEvent = handleInputEvent(poll);
			eventList.add(returnEvent);
		}

		return eventList;
	}
}
