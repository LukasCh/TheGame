package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Terrain extends GameEntity {
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;

	public Terrain() {
		super();
		this.x = Integer.valueOf(Constants.centerX);
		this.y = Integer.valueOf(Constants.centerY + Constants.centerY / 2);
		this.width = Integer.valueOf(Constants.centerX * 2);
		this.height = Integer.valueOf(Constants.centerY);
	}

	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(getGraphicsX(v), getGraphicsY(v), getGraphicsWidth(), getGraphicsHeight());
		}
	}

	public void update(int delta) {

	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getSpeedX() {
		return null;
	}

	public Integer getSpeedY() {
		return null;
	}

	public Integer getSpeedLimitX() {
		return null;
	}

	public Integer getSpeedLimitY() {
		return null;
	}
}
