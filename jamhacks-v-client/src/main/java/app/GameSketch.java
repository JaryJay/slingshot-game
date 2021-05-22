package app;

import processing.core.PApplet;

public class GameSketch extends PApplet {

	private int windowLength;
	private int windowHeight;
	private boolean sentTest;

	public GameSketch(int windowLength, int windowHeight) {
		this.windowLength = windowLength;
		this.windowHeight = windowHeight;
	}

	@Override
	public void settings() {
		size(windowLength, windowHeight);
	}

	@Override
	public void setup() {
	}

	@Override
	public void draw() {
		if (!sentTest) {

		}
	}

	public void run(String string) {
		String[] processingArgs = { string };
		runSketch(processingArgs, this);
	}

}
