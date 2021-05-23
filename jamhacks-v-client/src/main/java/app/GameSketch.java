package app;

import java.util.Queue;

import context.GameContextWrapper;
import event.input.AbstractGameInputEvent;
import event.input.KeyPressedGameInputEvent;
import event.input.KeyRepeatedGameInputEvent;
import event.input.MouseDraggedGameInputEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import processing.core.PApplet;

public class GameSketch extends PApplet {

	private int windowLength;
	private int windowHeight;

	private boolean[] pressedKeys = new boolean[127];

	private GameContextWrapper wrapper;

	private Queue<AbstractGameInputEvent> inputBuffer;

	public GameSketch(int windowLength, int windowHeight, Queue<AbstractGameInputEvent> inputBuffer, GameContextWrapper wrapper) {
		this.windowLength = windowLength;
		this.windowHeight = windowHeight;
		this.wrapper = wrapper;
		this.inputBuffer = inputBuffer;
		wrapper.setPApplet(this);
	}

	@Override
	public void settings() {
		size(windowLength, windowHeight);
	}

	@Override
	public void setup() {
		wrapper.init(this);
	}

	@Override
	public void draw() {
		wrapper.getContext().getVisuals().display(this);
	}

	public void run() {
		String[] processingArgs = { "lol" };
		runSketch(processingArgs, this);
	}

	@Override
	public void keyPressed() {
		if (keyCode >= 32 && keyCode <= 126 || keyCode == 10 || keyCode == 8) {
			if (pressedKeys[keyCode] == true) {
				KeyRepeatedGameInputEvent keyRepeatedEvent = new KeyRepeatedGameInputEvent(keyCode);
				inputBuffer.add(keyRepeatedEvent);
				return;
			}
			pressedKeys[keyCode] = true;
		}
		KeyPressedGameInputEvent keyPressedEvent = new KeyPressedGameInputEvent(keyCode);
		inputBuffer.add(keyPressedEvent);
	}

	@Override
	public void keyReleased() {
		if (keyCode >= 32 && keyCode <= 126 || keyCode == 10 || keyCode == 8) {
			pressedKeys[keyCode] = false;
		}
	}

	private boolean mouseHeld;

	@Override
	public void mousePressed() {
		if (mouseHeld)
			return;
		this.mouseHeld = true;
		MousePressedGameInputEvent mousePressedEvent = new MousePressedGameInputEvent(mouseX, mouseY);
		inputBuffer.add(mousePressedEvent);
	}

	@Override
	public void mouseReleased() {
		this.mouseHeld = false;
		MouseReleasedGameInputEvent mouseReleasedEvent = new MouseReleasedGameInputEvent(mouseX, mouseY);
		inputBuffer.add(mouseReleasedEvent);
	}

	@Override
	public void mouseDragged() {
		MouseDraggedGameInputEvent mouseDraggedEvent = new MouseDraggedGameInputEvent(mouseX, mouseY);
		inputBuffer.add(mouseDraggedEvent);
	}

}
