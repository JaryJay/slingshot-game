package map;

import math.Vector2f;

public class RectangularObstacleHitbox extends GameObstacleHitbox {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8652715515525641729L;

	public RectangularObstacleHitbox(Vector2f position, Vector2f dimensions) {
		this(position.x, position.y, dimensions.x, dimensions.y);

	}

	public RectangularObstacleHitbox(float px, float py, float width, float height) {
		super();
		// Top edge
		edges.add(new GameObstacleEdge(px, py, px + width, py));
		// Left edge
		edges.add(new GameObstacleEdge(px, py, px, py + height));
		// Bottom edge
		edges.add(new GameObstacleEdge(px, py + height, px + width, py + height));
		// Right edge
		edges.add(new GameObstacleEdge(px + width, py, px + width, py + height));
	}

}
