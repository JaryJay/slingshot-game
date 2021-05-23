package event.servertoclient;

import map.GameObstacle;

public class STCObstacleInfoEvent extends ServerToClientGameEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8883003231112667684L;
	private GameObstacle[] obstacles;

	public STCObstacleInfoEvent(GameObstacle[] obstacles) {
		this.obstacles = obstacles;
	}

	public GameObstacle[] getObstacles() {
		return obstacles;
	}

}
