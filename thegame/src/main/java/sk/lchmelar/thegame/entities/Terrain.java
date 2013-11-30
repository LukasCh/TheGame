package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.math.BigDecimal;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Terrain extends GameEntity {
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal width;
	private BigDecimal height;
		
	public Terrain() {
		super();
		this.x = BigDecimal.valueOf(Constants.centerX);
		this.y = BigDecimal.valueOf(Constants.centerY + Constants.centerY/2);
		this.width = BigDecimal.valueOf(Constants.centerX * 2);
		this.height = BigDecimal.valueOf(Constants.centerY);
	}
	
	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(getGraphicsX(v), getGraphicsY(v), getWidthInteger(), getHeightInteger());
		}
	}

	public void update(int delta) {
		
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
}
