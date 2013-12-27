package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Actor extends GameEntity {
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Integer speedX;
	private Integer speedY;

	public Actor(Integer x, Integer y, Integer width, Integer height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speedX = 0;
		this.speedY = 0;
	}

	@Override
	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)) {
			g.setColor(Color.BLUE);
			g.fillRect(getGraphicsX(v), getGraphicsY(v), getGraphicsWidth(), getGraphicsHeight());
		}
	}

	public void changeSpeedX(Integer value) {
		if (Math.abs(speedX + value) >= getSpeedLimitX()) {
			speedX = getSpeedLimitX() * Integer.signum(speedX);
		} else {
			speedX = speedX + value;
		}

	}

	public void changeSpeedY(Integer value) {
		if (Math.abs(speedY + value) >= getSpeedLimitY()) {
			speedY = getSpeedLimitY() * Integer.signum(speedY);
		} else {
			speedY = speedY + value;
		}
	}

	@Override
	public void update(int delta) {
		x = Math.round(x + speedX * delta / 1000.0f);
		y = Math.round(y + speedY * delta / 1000.0f);
		int speedXReduction = Math.round(Integer.signum(speedX) * getHeight() * 3.5f * delta / 1000.0f);
		if (Integer.signum(speedXReduction) == Integer.signum(speedX - speedXReduction)) {
			changeSpeedX(-speedXReduction);
		} else {
			speedX = 0;
		}
		int speedYReduction = Math.round(Integer.signum(speedY) * getWidth() * 3.5f * delta / 1000.0f);
		if (Integer.signum(speedYReduction) == Integer.signum(speedY - speedYReduction)) {
			changeSpeedY(-speedYReduction);
		} else {
			speedY = 0;
		}
	}

	public Projectile shoot(Integer targetX, Integer targetY) {
		double vectorX = targetX - getX() / Constants.precisionFloat;
		double vectorY = targetY - getY() / Constants.precisionFloat;

		double length = Math.sqrt(vectorX * vectorX + vectorY * vectorY);

		Projectile p = new Projectile();

		p.setX(getX());
		p.setY(getY());

		p.setSpeedX((int) Math.round(vectorX / length * 300 * Constants.precisionFloat));
		p.setSpeedY((int) Math.round(vectorY / length * 300 * Constants.precisionFloat));

		p.setWidth(3 * Constants.precision);
		p.setHeight(3 * Constants.precision);

		return p;

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
		return speedX;
	}

	public Integer getSpeedY() {
		return speedY;
	}

	public Integer getSpeedLimitX() {
		return 100 * Constants.precision;
	}

	public Integer getSpeedLimitY() {
		return 100 * Constants.precision;
	}

}
