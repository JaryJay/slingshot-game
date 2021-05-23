package visuals;

import actor.GameActor;
import data.GameData;
import data.SlingShotData;
import math.Vector2f;
import processing.core.PApplet;
import processing.core.PImage;

public class SlingShotVisuals extends GameVisuals {

	private PImage loadingScreenImage;
	private SlingShotData slingShotData;

	public SlingShotVisuals(GameData data) {
		super(data);
		this.slingShotData = (SlingShotData) data;
	}

	@Override
	public void init(PApplet p) {
		loadingScreenImage = p.loadImage("src/main/resources/loadingplaceholder.jpg");
	}

	@Override
	public void display(PApplet p) {
//		p.image(loadingScreenImage, 0, 0, p.width, p.height);
		p.background(255);
		for (GameActor player : slingShotData.getCurrentState().getIdToActors().values()) {
			Vector2f pos = player.getPosition();
			p.ellipse(pos.x, pos.y, 40, 40);
		}
	}

}
