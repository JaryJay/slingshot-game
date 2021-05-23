package app;

import java.awt.event.KeyEvent;
import java.util.Queue;

import event.input.AbstractGameInputEvent;
import event.input.KeyPressedEvent;
import event.input.MousePressedGameInputEvent;
import event.input.MouseReleasedGameInputEvent;
import logic.GameData;
import logic.GameLogicTimer;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class GameSketch extends PApplet {

	private int windowLength;
	private int windowHeight;
	private GameData data;
	private GameLogicTimer timer;
	private int screen;
	private PImage mainScreenImage;
	private PImage loadingScreenImage;
	private PFont f;
	private String textInput = "";
	private String username = "";
	private int unsernameIndent;
	private boolean sentTest;
	private Queue<AbstractGameInputEvent> inputBuffer;

	public GameSketch(int windowLength, int windowHeight, Queue<AbstractGameInputEvent> inputBuffer) {
		this.windowLength = windowLength;
		this.windowHeight = windowHeight;
		data = new GameData();
		// timer = new GameLogicTimer(new GameLogic(data), new TimeAccumulator());
		screen = 1;
		this.inputBuffer = inputBuffer;
	}

	@Override
	public void settings() {
		size(windowLength, windowHeight);
	}

	@Override
	public void setup() {
		mainScreenImage = loadImage("src/main/resources/SlingshotMainScreen.jpg");
		loadingScreenImage = loadImage("src/main/resources/loadingplaceholder.jpg");
		unsernameIndent = 200;
		f = createFont("Acme", 40);
	}

	@Override
	public void draw() {
//		GameState state = data.getCurrentState();
//		Collection<GameActor> actors = state.getIdToActors().values();
//		for (GameActor actor : actors) {
//			Colour.fill(actor.getColour(), this);
//		}
		if (data.getCurrentScreen() == 1) {
			disiplayMainMenu();
		} else if (data.getCurrentScreen() == 2) {

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
		if (key == '\n' && textInput.length() > 0) {
			username = textInput;
			textInput = "";
			data.setCurrentScreen(2);
		} else if (keyCode == KeyEvent.VK_BACK_SPACE && textInput.length() >= 1) {
			textInput = textInput.substring(0, textInput.length() - 1);

		} else if (keyCode >= 32 && textInput.length() <= 20)

		{
			textInput = textInput + key;
		}
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
		MousePressedGameInputEvent mousePressedEvent = new MousePressedGameInputEvent(mouseX, mouseY);
	}

	@Override
	public void mouseReleased() {
		MouseReleasedGameInputEvent mouseReleasedEvent = new MouseReleasedGameInputEvent(mouseX, mouseY);
	}

	private void disiplayMainMenu() {
		image(mainScreenImage, 0, 0);
		textFont(f);
		fill(0);

		text(textInput, unsernameIndent, 580);
		if (textInput.length() <= 0) {
			fill(0, 20);
			tint(255, 50);
			text("Username", unsernameIndent, 579);
		}
	}

	private void displayLoadingScreen() {
		image(loadingScreenImage, 0, 0);
	}

}
