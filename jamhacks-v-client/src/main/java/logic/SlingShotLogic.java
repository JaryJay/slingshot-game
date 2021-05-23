package logic;

import java.awt.event.KeyEvent;
import java.util.Queue;

import actor.Player;
import data.GameData;
import data.SlingShotData;
import event.clienttoserver.ClientToServerGameEvent;
import event.clienttoserver.InputFrameEvent;
import event.input.AbstractGameInputEvent;
import event.input.GameInputFrame;
import event.input.KeyPressedGameInputEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import event.inputfactory.SpawnRequestEvent;
import event.inputfactory.VelocityChangeEvent;
import event.servertoclient.ServerToClientGameEvent;
import math.Vector2f;
import state.GameState;
import util.id.IdGenerator;

public class SlingShotLogic extends GameLogic {

	private SlingShotData data;

	public SlingShotLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		this.data = (SlingShotData) data;
		if (this.data.getCurrentInputFrame() == null) {
			this.data.setCurrentInputFrame(new GameInputFrame());
		}
		SpawnRequestEvent spawnRequest = new SpawnRequestEvent(IdGenerator.generateEventId(), System.currentTimeMillis());
		spawnRequest.setOriginalPlayerId(this.data.getUserId());
		Player newPlayer = new Player(IdGenerator.generateActorId());
		newPlayer.setColour(189, 9, 144);
		spawnRequest.setNewPlayer(newPlayer);
		this.data.getCurrentInputFrame().getEvents().add(spawnRequest);
	}

	@Override
	public void update() {
		super.update();
		GameState currentState = data.getCurrentState();
		data.getPastStates().add(currentState);
		data.setCurrentState(currentState);
		InputFrameEvent inputFrameEvent = new InputFrameEvent(IdGenerator.generateEventId(), System.currentTimeMillis());
		inputFrameEvent.setInputFrame(data.getCurrentInputFrame());
		ctsEventBuffer.add(inputFrameEvent);
		data.setCurrentInputFrame(new GameInputFrame());
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
	protected void handleCTSGameEvent(ClientToServerGameEvent event) {
		if (event instanceof VelocityChangeEvent) {
		}
	}

	@Override
	protected void handleSTCGameEvent(ServerToClientGameEvent poll) {

	}
}
