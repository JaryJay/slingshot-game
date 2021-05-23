package event.servertoclient;

import map.GameObstacle;

public class STCObstacleInfoEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8883003231112667684L;
	private GameObstacle obstacle;

	public STCObstacleInfoEvent(GameObstacle obstacle) {
		this.obstacle = obstacle;
	}

	public GameObstacle getObstacles() {
		return obstacle;
	}

	@Override
	public String getDescription() {
		return "Obstacle info";
	}

}
