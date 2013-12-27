package sk.lchmelar.thegame.interfaces;

import java.awt.Graphics2D;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;

public abstract class GameEntity {
	public abstract void render(Graphics2D g, Viewport v);

	public abstract void update(int delta);

	public abstract Integer getX();

	public abstract Integer getY();

	public abstract Integer getWidth();

	public abstract Integer getHeight();

	public abstract Integer getSpeedX();

	public abstract Integer getSpeedY();

	public abstract Integer getSpeedLimitX();

	public abstract Integer getSpeedLimitY();

	protected boolean isVisibleToViewport(Viewport v) {
		if (this.getGraphicsX(v) - this.getGraphicsWidth() / 2 >= v.getX() || this.getGraphicsX(v) + this.getGraphicsWidth() / 2 <= v.getX() + v.getWidth()
				|| this.getGraphicsY(v) - this.getGraphicsHeight() / 2 >= v.getY()
				|| this.getGraphicsY(v) + this.getGraphicsHeight() / 2 <= v.getY() + v.getHeight()) {
			return true;
		}

		return false;
	}

	protected Integer getGraphicsX(Viewport v) {
		return Math.round(getX() / Constants.precisionFloat - (getWidth() / 2.0f) / Constants.precisionFloat - v.getX());
	}

	protected Integer getGraphicsY(Viewport v) {
		return Math.round(getY() / Constants.precisionFloat - (getHeight() / 2.0f) / Constants.precisionFloat - v.getY());
	}

	protected Integer getGraphicsWidth() {
		return Math.round(getWidth() / Constants.precisionFloat);
	}

	protected Integer getGraphicsHeight() {
		return Math.round(getHeight() / Constants.precisionFloat);
	}
}
