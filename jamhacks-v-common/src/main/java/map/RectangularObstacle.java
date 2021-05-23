package map;

import math.Vector2f;

public class RectangularObstacle extends GameObstacle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5465398393456193042L;

	public RectangularObstacle(Vector2f position, Vector2f dimensions) {
		super("../jamhacks-v-common/src/main/resources/obstacleImage.jpg", position, dimensions);
	}

}
