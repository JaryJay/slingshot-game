package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameObstacleHitbox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360386717927701687L;
	protected List<GameObstacleEdge> edges;

	public GameObstacleHitbox() {
		edges = new ArrayList<>();
	}

	public List<GameObstacleEdge> getEdges() {
		return edges;
	}

}
