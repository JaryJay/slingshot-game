package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import math.Vector2f;

public class GameMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 826985755187414068L;
	protected List<GameObstacle> obstacles;
	private int wallThickness = 80;

	public GameMap() {
		obstacles = new ArrayList<>();
		generateDefaultMap();
	}

	public List<GameObstacle> getObstacles() {
		return obstacles;
	}

	public void generateDefaultMap() {
		RectangularObstacle leftwall = new RectangularObstacle(new Vector2f(0, 0), new Vector2f(wallThickness, 3600));
		RectangularObstacle topwall = new RectangularObstacle(new Vector2f(0, 0), new Vector2f(4800, wallThickness));
		RectangularObstacle rightwall = new RectangularObstacle(new Vector2f(4800 - wallThickness, 0),
				new Vector2f(wallThickness, 4800));
		RectangularObstacle bottomwall = new RectangularObstacle(new Vector2f(0, 3600 - wallThickness),
				new Vector2f(4800, wallThickness));
		obstacles.add(leftwall);
		obstacles.add(topwall);
		obstacles.add(rightwall);
		obstacles.add(bottomwall);

		// 2
		obstacles.add(new RectangularObstacle(new Vector2f(500, 2580), new Vector2f(840, wallThickness)));

		// 3
		obstacles.add(new RectangularObstacle(new Vector2f(920, 2660), new Vector2f(wallThickness, 500)));

		// 4
		obstacles.add(new RectangularObstacle(new Vector2f(920, 3160), new Vector2f(420, wallThickness)));

		// 5
		obstacles.add(
				new RectangularObstacle(new Vector2f(1760, 3600 - wallThickness), new Vector2f(wallThickness, -650)));

		// 6
		obstacles.add(new RectangularObstacle(new Vector2f(1760, 2950), new Vector2f(-420, wallThickness * -1)));

		// 7
		obstacles.add(new RectangularObstacle(new Vector2f(2180, 2500), new Vector2f(wallThickness, 700)));

		// 8
		obstacles.add(new RectangularObstacle(new Vector2f(2180, 2500), new Vector2f(-300, wallThickness)));

		// 9
		obstacles.add(new RectangularObstacle(new Vector2f(2600, 3600), new Vector2f(wallThickness, -500)));

		// 10
		obstacles.add(new RectangularObstacle(new Vector2f(3000, 3600), new Vector2f(wallThickness, -500)));

		// 11
		obstacles.add(new RectangularObstacle(new Vector2f(3000, 3100), new Vector2f(300, wallThickness)));

		// 12
		obstacles.add(new RectangularObstacle(new Vector2f(3000, 2700), new Vector2f(800, wallThickness)));

		// 13
		obstacles.add(new RectangularObstacle(new Vector2f(3000, 2700), new Vector2f(800, wallThickness)));

		// 14
		obstacles.add(
				new RectangularObstacle(new Vector2f(3800, 2700 + wallThickness), new Vector2f(wallThickness, -500)));

		// 15
		obstacles.add(new RectangularObstacle(new Vector2f(3700, 3350), new Vector2f(wallThickness, -300)));

		// 16
		obstacles.add(new RectangularObstacle(new Vector2f(4100, 3600), new Vector2f(wallThickness, -700)));

		// 17
		obstacles.add(new RectangularObstacle(new Vector2f(4800, 2500), new Vector2f(-500, wallThickness)));

		// 18
		obstacles.add(new RectangularObstacle(new Vector2f(4800, 2500), new Vector2f(-500, wallThickness)));

		// 19
		obstacles.add(new RectangularObstacle(new Vector2f(3500, 2300), new Vector2f(-500, wallThickness)));

		// 20
		obstacles.add(new RectangularObstacle(new Vector2f(500, 1500), new Vector2f(1200, wallThickness)));

		// 21
		obstacles.add(new RectangularObstacle(new Vector2f(1000, 1800), new Vector2f(wallThickness, 400)));

		// 22
		obstacles.add(new RectangularObstacle(new Vector2f(0, 2100), new Vector2f(600, wallThickness)));

		// 23
		obstacles.add(new RectangularObstacle(new Vector2f(1700, 1500), new Vector2f(wallThickness, 400)));

		// 24
		obstacles.add(new RectangularObstacle(new Vector2f(0, 1000), new Vector2f(700, wallThickness)));

		// 25
		obstacles.add(new RectangularObstacle(new Vector2f(1200, 0), new Vector2f(wallThickness, 800)));

		// 26
		obstacles.add(new RectangularObstacle(new Vector2f(600, 300), new Vector2f(wallThickness, 400)));

		// 27
		obstacles.add(new RectangularObstacle(new Vector2f(400, 450), new Vector2f(400, wallThickness)));

		// 28
		obstacles.add(new RectangularObstacle(new Vector2f(1800, 300), new Vector2f(400, wallThickness)));

		// 29
		obstacles.add(new RectangularObstacle(new Vector2f(1800, 300), new Vector2f(wallThickness, 600)));

		// 31
		obstacles.add(new RectangularObstacle(new Vector2f(1800, 1100), new Vector2f(1200, wallThickness)));

		// 32
		obstacles.add(new RectangularObstacle(new Vector2f(2500, 0), new Vector2f(wallThickness, 700)));

		// 33
		obstacles.add(new RectangularObstacle(new Vector2f(2500, 0), new Vector2f(wallThickness, 700)));

		// 34
		obstacles.add(new RectangularObstacle(new Vector2f(2500, 0), new Vector2f(wallThickness, 700)));

		// 35
		obstacles.add(new RectangularObstacle(new Vector2f(2900, 500), new Vector2f(600, wallThickness)));

		// 36
		obstacles.add(new RectangularObstacle(new Vector2f(3500, 500), new Vector2f(wallThickness, 300)));

		// 37
		obstacles.add(new RectangularObstacle(new Vector2f(3800, 500), new Vector2f(wallThickness, 400)));

		// 38
		obstacles.add(new RectangularObstacle(new Vector2f(3800, 900), new Vector2f(500, wallThickness)));

		// 39
		obstacles.add(new RectangularObstacle(new Vector2f(4800, 1600), new Vector2f(-900, wallThickness)));

		// 40
		obstacles.add(new RectangularObstacle(new Vector2f(2980, 1550), new Vector2f(wallThickness, 400)));

		// 41
		obstacles.add(new RectangularObstacle(new Vector2f(3300, 1100), new Vector2f(300, wallThickness)));

		// 41
		obstacles.add(new RectangularObstacle(new Vector2f(3600, 1100), new Vector2f(wallThickness, 300)));
	}

}
