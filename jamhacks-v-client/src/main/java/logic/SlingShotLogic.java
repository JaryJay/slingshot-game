package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import data.GameData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedGameInputEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import event.inputfactory.VelocityChangeEvent;
import event.servertoclient.ServerToClientGameEvent;
import math.Vector2f;
import state.MutableGameState;

public class SlingShotLogic extends GameLogic {

	private SlingShotData data;

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

	private boolean aimingShot;
	private Vector2f mousePosOnClick;

	@Override
	protected ClientToServerGameEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		if (inputEvent instanceof KeyPressedGameInputEvent) {
			VelocityChangeEvent velocityChangeEvent = null;
			KeyPressedGameInputEvent keyPressedEvent = (KeyPressedGameInputEvent) inputEvent;
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
			MousePressedGameInputEvent mousePressedEvent = (MousePressedGameInputEvent) inputEvent;

			if (450 > mousePressedEvent.GetMousePos().x && mousePressedEvent.GetMousePos().x < 480) {
				if (330 > mousePressedEvent.GetMousePos().y && mousePressedEvent.GetMousePos().y < 390) {
					this.aimingShot = true;
					mousePosOnClick = new Vector2f(mousePressedEvent.GetMousePos().x, mousePressedEvent.GetMousePos().y);
				}
			}
		} else if (inputEvent instanceof MouseReleasedGameInputEvent) {
			MouseReleasedGameInputEvent mouseReleasedEvent = (MouseReleasedGameInputEvent) inputEvent;

			if (this.aimingShot) {
				Vector2f aimVector = new Vector2f(mouseReleasedEvent.GetMousePos().x - mousePosOnClick.x, mouseReleasedEvent.GetMousePos().y - mousePosOnClick.y);
			}
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
