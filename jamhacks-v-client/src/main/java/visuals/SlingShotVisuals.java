package visuals;

import actor.GameActor;
import data.GameData;
import data.SlingShotData;
import map.GameMap;
import map.GameObstacle;
import map.RectangularObstacle;
import math.Vector2f;
import processing.core.PApplet;
import state.GameState;

public class SlingShotVisuals extends GameVisuals {

//	private PImage loadingScreenImage;
	private SlingShotData slingShotData;

	public SlingShotVisuals(GameData data) {
		super(data);
		this.slingShotData = (SlingShotData) data;
	}

	@Override
	public void init(PApplet p) {
//		loadingScreenImage = p.loadImage("src/main/resources/loadingplaceholder.jpg");
	}

	@Override
	public void display(PApplet p) {
		GameState state = slingShotData.getCurrentState();
		p.background(255);
		for (GameActor player : slingShotData.getCurrentState().getActorIdToActors().values()) {
			Vector2f pos = player.getPosition();
			p.ellipse(pos.x, pos.y, 40, 40);
		}

		GameMap map = state.getMap();
		if (map == null) {
			return;
		}
		for (GameObstacle obstacle : map.getObstacles()) {
			if (obstacle instanceof RectangularObstacle) {
				RectangularObstacle rObstacle = (RectangularObstacle) obstacle;
				p.rect(rObstacle.getPosition().x, rObstacle.getPosition().y, rObstacle.getDimensions().x,
						rObstacle.getDimensions().y);
			}

		}

		if (slingShotData.isAimingShot()) {
			Vector2f aimVector = new Vector2f(480 - p.mouseX, 360 - p.mouseY);
			float diagonal = aimVector.length();
			int amountBalls = (int) diagonal / 30;

			for (int i = 0; i < amountBalls; i++) {
				aimVector.setLength(i * 40);
				p.ellipse(480 + aimVector.x, 360 + aimVector.y, 20, 20);
			}
		}
	}

}
