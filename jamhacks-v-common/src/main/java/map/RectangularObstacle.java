package map;

import math.Vector2f;
import processing.core.PApplet;

public class RectangularObstacle extends GameObstacle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5465398393456193042L;

	public RectangularObstacle(Vector2f position, Vector2f dimensions) {
		// TODO
		super(new RectangularObstacleHitbox(position, dimensions), null, position, dimensions);
	}

	public void display(PApplet p) {
		p.image(image, position.x, position.y, dimensions.x, dimensions.y);
	}

}
