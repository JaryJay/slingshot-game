package visuals;

import data.GameData;
import data.MainScreenData;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class MainScreenVisuals extends GameVisuals {

	private PImage mainScreenImage;
	private PFont f;
	private int unsernameIndent = 200;
	private MainScreenData mainScreenData;

	public MainScreenVisuals(GameData data) {
		super(data);
		mainScreenData = ((MainScreenData) data);
	}

	@Override
	public void init(PApplet p) {
		mainScreenImage = p.loadImage("src/main/resources/SlingshotMainScreen.jpg");
		f = p.createFont("Arial", 40);
	}

	@Override
	public void display(PApplet p) {
		p.image(mainScreenImage, 0, 0);
		p.textFont(f);
		p.fill(0);

		p.text(mainScreenData.getTextInput(), unsernameIndent, 580);
		if (mainScreenData.getTextInput().length() <= 0) {
			p.fill(0, 20);
			p.tint(255, 50);
			p.text("Username", unsernameIndent, 579);
		}
	}

}
