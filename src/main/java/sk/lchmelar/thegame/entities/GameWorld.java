package sk.lchmelar.thegame.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class GameWorld extends GameEntity {
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;

	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();

	private GameEntity localPlayer;

	public GameWorld() {
		super();
		entities.add(new Terrain());
		localPlayer = new Actor(Constants.centerX, Constants.centerY, 20 * Constants.precision, 20 * Constants.precision);
		entities.add(localPlayer);
	}

	public void render(Graphics2D g, Viewport v) {
		synchronized (entities) {
			for (GameEntity entity : entities) {
				entity.render(g, v);
			}
		}
	}

	public void update(int delta) {
		synchronized (entities) {
			for (GameEntity entity : entities) {
				entity.update(delta);
			}
		}
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

	public ArrayList<GameEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<GameEntity> entities) {
		this.entities = entities;
	}

	public void addEntity(GameEntity entity) {
		synchronized (entities) {
			entities.add(entity);
		}
	}

	public GameEntity getLocalPlayer() {
		return localPlayer;
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
