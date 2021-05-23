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
		loadingScreenImage = p.loadImage("src/main/resources/loadingplaceholder.jpg");
	}

	@Override
	public void display(PApplet p) {
		p.image(loadingScreenImage, 0, 0, p.width, p.height);
	}

}
