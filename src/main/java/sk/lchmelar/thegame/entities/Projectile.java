package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Projectile extends GameEntity {
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Integer speedX;
	private Integer speedY;

	public Projectile(Integer x, Integer y, Integer speedX, Integer speedY, Integer width, Integer height) {
		super();
		this.x = Integer.valueOf(x);
		this.y = Integer.valueOf(y);
		this.width = Integer.valueOf(width);
		this.height = Integer.valueOf(height);
		this.speedX = Integer.valueOf(speedX);
		this.speedY = Integer.valueOf(speedY);
	}

	public Projectile() {

	}

	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)) {
			g.setColor(Color.RED);
			g.fillOval(getGraphicsX(v), getGraphicsY(v), getGraphicsWidth(), getGraphicsHeight());
		}
	}

	public void update(int delta) {
		// System.out.println(delta);
		x = Math.round(x + speedX * delta / 1000.0f);
		y = Math.round(y + speedY * delta / 1000.0f);

		speedY = Math.round(speedY - Constants.gravAcc * delta / 1000.0f);

		speedX = Math.round(speedX - Integer.signum(speedX) * getHeight() / 10.0f * (delta / 1000.0f));
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

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getSpeedX() {
		return speedX;
	}

	public void setSpeedX(Integer speedX) {
		this.speedX = speedX;
	}

	public Integer getSpeedY() {
		return speedY;
	}

	public void setSpeedY(Integer speedY) {
		this.speedY = speedY;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getSpeedLimitX() {
		return null;
	}

	public Integer getSpeedLimitY() {
		return null;
	}

}
