package sk.lchmelar.thegame.entities;

import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.util.ArrayList;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class GameWorld extends GameEntity {
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal width;
	private BigDecimal height;
	
	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	
	private GameEntity localPlayer;
	
	public GameWorld () {
		super();
		entities.add(new Terrain());
		localPlayer = new Actor(Constants.centerX, Constants.centerY, 20, 20);
		entities.add(localPlayer);
	}
	
	public void render(Graphics2D g, Viewport v) {
		synchronized(entities){
			for(GameEntity entity: entities) {
				entity.render(g, v);
			}		
		}
	}

	public void update(int delta) {
		synchronized(entities){
			for(GameEntity entity: entities) {
				entity.update(delta);
			}
		}
	}

	public BigDecimal getX() {
		return x;	
	}	
	
	public BigDecimal getY() {
		return y;	
	}

	public BigDecimal getWidth() {
		return width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public ArrayList<GameEntity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<GameEntity> entities) {
		this.entities = entities;
	}	
	
	public void addEntity(GameEntity entity) {
		synchronized(entities){
			entities.add(entity);
		}
	}

	public GameEntity getLocalPlayer() {
		return localPlayer;
	}	
}
