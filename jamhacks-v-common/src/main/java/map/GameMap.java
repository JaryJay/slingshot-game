package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 826985755187414068L;
	protected List<GameObstacle> obstacles;

	public GameMap() {
		obstacles = new ArrayList<>();
	}

	public GameMap(List<GameObstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public List<GameObstacle> getObstacles() {
		return obstacles;
	}

}
