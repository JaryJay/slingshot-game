package math;

import java.io.Serializable;
import java.util.Objects;

public class Vector2f implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6296785327270628381L;
	public float x;
	public float y;

	public Vector2f(float ix, float iy) {
		x = ix;
		y = iy;
	}

	public Vector2f(Vector2f v) {
		x = v.x;
		y = v.y;
	}

	public Vector2f add(Vector2f v) {
		x = v.x + x;
		y = v.y + y;
		return this;
	}

	public Vector2f add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public Vector2f scale(float f) {
		x = x * f;
		y = y * f;
		return this;
	}

	public Vector2f copy() {
		Vector2f v = new Vector2f(x, y);
		return v;
	}

	public Vector2f sub(Vector2f v) {
		x = x - v.x;
		y = y - v.y;
		return this;
	}

	public Vector2f invert() {
		x = x * -1;
		y = y * -1;
		return this;
	}

	public Vector2f reflect(Vector2f mirror) {
		Vector2f ac = mirror.copy().scale(1 / (mirror.length() * mirror.length())).scale(this.dot(mirror));
		Vector2f cb = this.copy().sub(ac);
		Vector2f cbb = cb.invert();
		Vector2f abb = ac.add(cbb);
		x = abb.x;
		y = abb.y;

		return this;
	}

	public float dot(Vector2f v) {
		return x * v.x + y * v.y;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float lengthSqaured() {
		return x * x + y * y;
	}

	public Vector2f normalize() {
		float x = length();
		if (x == 0) {
			return set(1, 0);
		}
		scale(1 / x);
		return this;
	}

	public Vector2f setLength(float len) {
		normalize();
		scale(len);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Vector2f c = (Vector2f) o;
		boolean xSame = c.x == x;
		boolean ySame = c.y == y;
		return xSame && ySame;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public Vector2f set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2f set(Vector2f newPosition) {
		x = newPosition.x;
		y = newPosition.y;
		return newPosition;
	}

}
