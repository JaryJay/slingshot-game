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
import event.input.MouseDraggedGameInputEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import event.inputfactory.AimEvent;
import event.inputfactory.ShootEvent;
import event.inputfactory.SophisticatedInputEvent;
import event.inputfactory.SpawnRequestEvent;
import event.inputfactory.VelocityChangeEvent;
import event.servertoclient.ServerToClientGameEvent;
import math.Vector2f;
import state.GameState;
import state.GameStateExtrapolator;
import util.id.IdGenerator;

public class SlingShotLogic extends GameLogic {

	private SlingShotData data;

	public SlingShotLogic(GameData data, Queue<AbstractGameInputEvent> inputBuffer, Queue<ClientToServerGameEvent> ctsEventBuffer, Queue<ServerToClientGameEvent> stcEventBuffer) {
		super(data, inputBuffer, ctsEventBuffer, stcEventBuffer);
		this.data = (SlingShotData) data;
		if (this.data.getCurrentInputFrame() == null) {
			this.data.setCurrentInputFrame(new GameInputFrame(this.data.getCurrentState().getId()));
		}
		SpawnRequestEvent spawnRequest = new SpawnRequestEvent();
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
		data.setCurrentState(GameStateExtrapolator.getNextState(currentState, data.getCurrentInputFrame()));
		if (!data.getCurrentInputFrame().getEvents().isEmpty()) {
			InputFrameEvent inputFrameEvent = new InputFrameEvent();
			inputFrameEvent.setInputFrame(data.getCurrentInputFrame());
			ctsEventBuffer.add(inputFrameEvent);
		}
		data.setCurrentInputFrame(new GameInputFrame(this.data.getCurrentState().getId()));
	}

	@Override
	protected SophisticatedInputEvent handleInputEvent(AbstractGameInputEvent inputEvent) {
		SophisticatedInputEvent result = null;
		if (inputEvent instanceof KeyPressedGameInputEvent) {
			result = handleKeyPressed(inputEvent);
		} else if (inputEvent instanceof MousePressedGameInputEvent) {
			result = handleMousePressed(inputEvent);
		} else if (inputEvent instanceof MouseReleasedGameInputEvent) {
			result = handleMouseReleased(inputEvent);
		}
		if (result != null) {
			result.setPlayerId(data.getPlayerId());
		}
		return result;
	}

	private SophisticatedInputEvent handleMouseReleased(AbstractGameInputEvent inputEvent) {
		ShootEvent shootEvent = null;

		if (System.currentTimeMillis() - data.getLastShot() < 1000)
			return null;
		data.setLastShot(System.currentTimeMillis());

		MouseReleasedGameInputEvent mouseReleasedEvent = (MouseReleasedGameInputEvent) inputEvent;

		if (data.isAimingShot()) {
			Vector2f mousePos = mouseReleasedEvent.getMousePos();
			Vector2f mousePosOnClick = data.getMousePosOnClick();
			Vector2f aimVector = new Vector2f(mousePos.x - mousePosOnClick.x, mousePos.y - mousePosOnClick.y);
			float distance = (float) Math.sqrt((aimVector.x * aimVector.x) + (aimVector.y * aimVector.y));
			float strength = distance / 20; // TODO
		}
		return shootEvent;
	}

	private AimEvent handleMousePressed(AbstractGameInputEvent inputEvent) {
		MousePressedGameInputEvent mousePressedEvent = (MousePressedGameInputEvent) inputEvent;

		if (450 > mousePressedEvent.GetMousePos().x && mousePressedEvent.GetMousePos().x < 480) {
			if (330 > mousePressedEvent.GetMousePos().y && mousePressedEvent.GetMousePos().y < 390) {
				data.setAimingShot(true);
				data.setMousePosOnClick(new Vector2f(mousePressedEvent.GetMousePos().x, mousePressedEvent.GetMousePos().y));
				return new AimEvent(data.getMousePosOnClick()); // TODO, not supposed to be mousePosOnClick
			}
		}
		return null;
	}

	private AimEvent handleMouseDragged(AbstractGameInputEvent inputEvent) {
		MouseDraggedGameInputEvent mouseDraggedEvent = (MouseDraggedGameInputEvent) inputEvent;

		if (data.isAimingShot()) {
			AimEvent aimEvent = new AimEvent(mouseDraggedEvent.GetCurrentMousePos());

			return aimEvent;
		}

		return null;
	}

	private SophisticatedInputEvent handleKeyPressed(AbstractGameInputEvent inputEvent) {
		SophisticatedInputEvent result;
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
			velocityChangeEvent = new VelocityChangeEvent(5f, 0f);
			break;
		}
		result = velocityChangeEvent;
		return result;
	}

	private SophisticatedInputEvent handleKeyReleased(AbstractGameInputEvent inputEvent) {
		SophisticatedInputEvent result;
		VelocityChangeEvent velocityChangeEvent = null;

		KeyPressedGameInputEvent keyPressedEvent = (KeyPressedGameInputEvent) inputEvent;

		switch (keyPressedEvent.getKeyCode()) {
		case KeyEvent.VK_W:
			velocityChangeEvent = new VelocityChangeEvent(0f, 5f);
			break;
		case KeyEvent.VK_S:
			velocityChangeEvent = new VelocityChangeEvent(0f, -5f);
			break;
		case KeyEvent.VK_A:
			velocityChangeEvent = new VelocityChangeEvent(5f, 0f);
			break;
		case KeyEvent.VK_D:
			velocityChangeEvent = new VelocityChangeEvent(-5f, 0f);
			break;
		}

		result = velocityChangeEvent;
		return result;
	}

	@Override
	protected void handleSophisticatedInputEvent(SophisticatedInputEvent event) {
		if (event instanceof VelocityChangeEvent) {
			data.getCurrentInputFrame().getEvents().add(event);
		}
	}

	@Override
	protected void handleSTCGameEvent(ServerToClientGameEvent poll) {

	}
}
