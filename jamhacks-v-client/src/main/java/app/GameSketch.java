package app;

import java.awt.event.KeyEvent;

import event.input.KeyPressedEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
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

	public void run() {
		String[] processingArgs = { "lol" };
		runSketch(processingArgs, this);
	}

	@Override
	public void keyPressed() {
		KeyPressedEvent KeyPressedGameInputEvent;
		if (keyCode == KeyEvent.VK_W) {
			KeyPressedGameInputEvent = new KeyPressedEvent(keyCode);
		} else if (keyCode == KeyEvent.VK_A) {
			KeyPressedGameInputEvent = new KeyPressedEvent(keyCode);
		} else if (keyCode == KeyEvent.VK_S) {
			KeyPressedGameInputEvent = new KeyPressedEvent(keyCode);
		} else if (keyCode == KeyEvent.VK_D) {
			KeyPressedGameInputEvent = new KeyPressedEvent(keyCode);
		} else if (keyCode == KeyEvent.VK_R) {
			KeyPressedGameInputEvent = new KeyPressedEvent(keyCode);
		}

	}

	@Override
	public void mousePressed() {
		MousePressedGameInputEvent MousePressedEvent = new MousePressedGameInputEvent(mouseX, mouseY);

	}

	@Override
	public void mouseReleased() {
		MouseReleasedGameInputEvent MouseReleasedEvent = new MouseReleasedGameInputEvent(mouseX, mouseY);
	}

}
