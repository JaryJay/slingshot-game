package visuals;

import data.GameData;
import processing.core.PApplet;
import processing.core.PImage;

public class LoadingScreenVisuals extends GameVisuals {

	private PImage loadingScreenImage;

	public LoadingScreenVisuals(GameData data) {
		super(data);
	}

	@Override
	public void init(PApplet p) {
		loadingScreenImage = p.loadImage("src/main/resources/loading.jpg");
	}

	@Override
	public void display(PApplet p) {
		p.background(50);
		p.fill(0);
		p.tint(255, 255);
		p.image(loadingScreenImage, 0, 0);
	}

}
