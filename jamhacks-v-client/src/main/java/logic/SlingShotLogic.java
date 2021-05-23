package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import data.GameData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import event.inputfactory.VelocityChangeEvent;
import event.servertoclient.ServerToClientGameEvent;
import state.MutableGameState;

public class SlingShotLogic extends GameLogic {

	private SlingShotData data;
	private Queue<AbstractGameInputEvent> inputBuffer;
	private Queue<ClientToServerGameEvent> ctsEventBuffer;
	private Queue<ServerToClientGameEvent> stcEventBuffer;

	public SlingShotLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		this.data = (SlingShotData) data;
	}

	@Override
	public void update() {
		super.update();
		MutableGameState currentState = data.getCurrentState();
		data.getPastStates().add(currentState.immutable());
		data.setCurrentState(currentState.getNextState());
	}

	@Override
	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedEvent) {
			VelocityChangeEvent velocityChangeEvent = null;
			KeyPressedEvent keyPressedEvent = (KeyPressedEvent) inputEvent;
			// TODO
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
			return velocityChangeEvent;
		} else if (inputEvent instanceof MousePressedGameInputEvent) {

		} else if (inputEvent instanceof MouseReleasedGameInputEvent) {
		}

		return null;
	}

	@Override
	protected void handleAllInputEvents() {
		while (!inputBuffer.isEmpty()) {
			AbstractGameInputEvent poll = inputBuffer.poll();
			ClientToServerGameEvent returnEvent = handleInputEvent(poll);
			if (returnEvent != null) {
				ctsEventBuffer.add(returnEvent);
			}
		}
	}

	@Override
	protected void handleAllSTCEvents() {
		while (!stcEventBuffer.isEmpty()) {
			ServerToClientGameEvent poll = stcEventBuffer.poll();
			handleSTCEvent(poll);
		}
	}

	@Override
	protected void handleSTCEvent(ServerToClientGameEvent poll) {

	}
}
