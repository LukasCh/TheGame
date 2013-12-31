package sk.lchmelar.thegame.graphics;

import java.awt.Rectangle;

public class Viewport {
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle rectangle;
	
	public Viewport(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rectangle = new Rectangle(0, 0, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return "{x = " + x + ", y = " + y + ", width = " + width + ", height = " + height + "}";
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
}
