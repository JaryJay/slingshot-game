package map;

import java.io.Serializable;

import math.Vector2f;
import processing.core.PApplet;
import processing.core.PImage;

public class GameObstacle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4143004735978913564L;
	protected GameObstacleHitbox hitbox;
	protected String imageUrl;
	protected transient PImage image;
	protected Vector2f position;
	protected Vector2f dimensions;

	public GameObstacle(GameObstacleHitbox hitbox, String imageUrl, Vector2f position, Vector2f dimensions) {
		this.hitbox = hitbox;
		this.imageUrl = imageUrl;
		this.position = position;
		this.dimensions = dimensions;
	}

	public GameObstacleHitbox getHitbox() {
		return hitbox;
	}

	public Vector2f getTopLeftEdge() {
		return position;
	}

	public Vector2f getDimensions() {
		return dimensions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void display(PApplet p) {
		if (image == null) {
			image = p.loadImage(imageUrl);
		}
		p.image(image, position.x, position.y, dimensions.x, dimensions.y);
	}

}
