package actor;

import java.io.Serializable;

import colour.Colour;
import math.Vector2f;
import processing.core.PApplet;
import util.id.HasId;
import util.id.IdGenerator;
import util.update.Updatable;

/**
 * A game object with a position and velocity.
 * 
 * @author Jay
 *
 */
public abstract class GameActor implements HasId, Updatable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1661780835951898474L;
	protected long id;
	protected Vector2f position;
	protected Vector2f velocity;
	protected float hitboxRadius;
	protected int colour = 255;

	/**
	 * Constructor that generates an ID.
	 */
	public GameActor() {
		id = IdGenerator.generateActorId();
	}

	protected GameActor(long id) {
		this.id = id;
	}

	/**
	 * Displays the actor using a {@link PApplet} p.
	 * 
	 * @param p the PApplet
	 */
	public abstract void display(PApplet p);

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public float getHitboxRadius() {
		return hitboxRadius;
	}

	public void setHitboxRadius(float hitboxRadius) {
		this.hitboxRadius = hitboxRadius;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public void setColour(int r, int g, int b) {
		this.colour = Colour.of(r, g, b);
	}

	public abstract GameActor copy();

	/**
	 * Adds velocity to the position. Usually, do not override this method with a
	 * subclass of {@link GameActor}.
	 */
	@Override
	public void update() {
		position.add(velocity);
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
