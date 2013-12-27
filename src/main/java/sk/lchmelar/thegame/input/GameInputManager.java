package sk.lchmelar.thegame.input;

import java.util.HashMap;
import java.util.Map;

import sk.lchmelar.thegame.TheGame;
import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.entities.Actor;

public class GameInputManager {
	public static final Integer LEFT = 0;
	public static final Integer RIGHT = 1;
	public static final Integer UP = 2;
	public static final Integer DOWN = 3;

	public static final Integer CAMERA_LEFT = 4;
	public static final Integer CAMERA_RIGHT = 5;
	public static final Integer CAMERA_UP = 6;
	public static final Integer CAMERA_DOWN = 7;

	public static final Integer LMB = 8;
	public static final Integer MMB = 9;
	public static final Integer RMB = 10;

	private Map<Integer, Object> keyMap = new HashMap<Integer, Object>();
	private Integer mouseX;
	private Integer mouseY;
	private TheGame theGame;

	public GameInputManager(TheGame theGame) {
		this.theGame = theGame;
		reset();
	}

	public void reset() {
		keyMap.put(LEFT, false);
		keyMap.put(RIGHT, false);
		keyMap.put(UP, false);
		keyMap.put(DOWN, false);

		keyMap.put(CAMERA_LEFT, false);
		keyMap.put(CAMERA_RIGHT, false);
		keyMap.put(CAMERA_UP, false);
		keyMap.put(CAMERA_DOWN, false);

		keyMap.put(LMB, false);
		keyMap.put(MMB, false);
		keyMap.put(RMB, false);
	}

	public void pressKey(Integer key) {
		keyMap.put(key, true);
	}

	public void releaseKey(Integer key) {
		keyMap.put(key, false);
	}

	public boolean isPressed(Integer key) {
		return (Boolean) keyMap.get(key);
	}

	public void handleInput() {
		if (isPressed(CAMERA_RIGHT)) {
			// System.out.println("D");
			if (theGame.getGraphicsRenderer().getViewport().getX() + theGame.getGraphicsRenderer().getViewport().getWidth() + Constants.cameraSpeed <= Constants.centerX * 2) {
				theGame.getGraphicsRenderer().getViewport().setX(theGame.getGraphicsRenderer().getViewport().getX() + Constants.cameraSpeed);
			}
		}
		if (isPressed(CAMERA_LEFT)) {
			// System.out.println("A");
			if (theGame.getGraphicsRenderer().getViewport().getX() - Constants.cameraSpeed >= 0) {
				theGame.getGraphicsRenderer().getViewport().setX(theGame.getGraphicsRenderer().getViewport().getX() - Constants.cameraSpeed);
			}
		}
		if (isPressed(CAMERA_UP)) {
			// System.out.println("W");
			if (theGame.getGraphicsRenderer().getViewport().getY() - Constants.cameraSpeed >= 0) {
				theGame.getGraphicsRenderer().getViewport().setY(theGame.getGraphicsRenderer().getViewport().getY() - Constants.cameraSpeed);
			}
		}
		if (isPressed(CAMERA_DOWN)) {
			// System.out.println("S");
			if (theGame.getGraphicsRenderer().getViewport().getY() + theGame.getGraphicsRenderer().getViewport().getHeight() + Constants.cameraSpeed <= Constants.centerY * 2) {
				theGame.getGraphicsRenderer().getViewport().setY(theGame.getGraphicsRenderer().getViewport().getY() + Constants.cameraSpeed);
			}
		}
		if (isPressed(UP)) {
			// System.out.println("D");
			((Actor) theGame.getGameWorld().getLocalPlayer()).changeSpeedY(Constants.precision * -1);
		}
		if (isPressed(DOWN)) {
			((Actor) theGame.getGameWorld().getLocalPlayer()).changeSpeedY(Constants.precision * 1);
		}
		if (isPressed(LEFT)) {
			((Actor) theGame.getGameWorld().getLocalPlayer()).changeSpeedX(Constants.precision * -1);
		}
		if (isPressed(RIGHT)) {
			((Actor) theGame.getGameWorld().getLocalPlayer()).changeSpeedX(Constants.precision * 1);
		}

		if (isPressed(LMB)) {
			theGame.getGameWorld().addEntity(
					((Actor) theGame.getGameWorld().getLocalPlayer()).shoot(theGame.getGraphicsRenderer().getViewport().getX() + getMouseX(), theGame
							.getGraphicsRenderer().getViewport().getY()
							+ getMouseY()));
		}
	}

	public Integer getMouseX() {
		return mouseX;
	}

	public void setMouseX(Integer mouseX) {
		this.mouseX = mouseX;
	}

	public Integer getMouseY() {
		return mouseY;
	}

	public void setMouseY(Integer mouseY) {
		this.mouseY = mouseY;
	}
}
