package map;

import java.io.Serializable;

import math.Vector2f;

public class GameObstacleEdge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348870123117370656L;
	protected Vector2f position1;
	protected Vector2f position2;

	public GameObstacleEdge(float px, float py, float nx, float ny) {
		this(new Vector2f(px, py), new Vector2f(nx, ny));
	}

	public GameObstacleEdge(Vector2f position, Vector2f normal) {
		this.position1 = position.copy().normalize();
		this.position2 = normal;
	}

	public Vector2f getPosition() {
		return position1;
	}

	public Vector2f getNormal() {
		return position2;
	}

}
