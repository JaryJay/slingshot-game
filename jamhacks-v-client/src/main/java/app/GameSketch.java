package app;

import java.awt.event.KeyEvent;
import java.util.Queue;

import event.input.AbstractGameInputEvent;
import event.input.KeyPressedEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import processing.core.PApplet;

public class GameSketch extends PApplet {

	private int windowLength;
	private int windowHeight;
	private boolean sentTest;
	private Queue<AbstractGameInputEvent> inputBuffer;

	public GameSketch(int windowLength, int windowHeight, Queue<AbstractGameInputEvent> inputBuffer) {
		this.windowLength = windowLength;
		this.windowHeight = windowHeight;
		this.inputBuffer = inputBuffer;
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

	private boolean wPressed;
	private boolean aPressed;
	private boolean sPressed;
	private boolean dPressed;

	@Override
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_W) {
			if (this.wPressed)
				return;
			this.wPressed = true;
		} else if (keyCode == KeyEvent.VK_A) {
			if (this.aPressed)
				return;
			this.aPressed = true;
		} else if (keyCode == KeyEvent.VK_S) {
			if (this.sPressed)
				return;
			this.sPressed = true;
		} else if (keyCode == KeyEvent.VK_D) {
			if (this.dPressed)
				return;
			this.dPressed = true;
		} else {
			return;
		}
		KeyPressedEvent keyPressedEvent = new KeyPressedEvent(keyCode);
		inputBuffer.add(keyPressedEvent);
	}

	@Override
	public void keyReleased() {
		if (keyCode == KeyEvent.VK_W) {
			this.wPressed = false;
		} else if (keyCode == KeyEvent.VK_A) {
			this.aPressed = false;
		} else if (keyCode == KeyEvent.VK_S) {
			this.sPressed = false;
		} else if (keyCode == KeyEvent.VK_D) {
			this.dPressed = false;
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
