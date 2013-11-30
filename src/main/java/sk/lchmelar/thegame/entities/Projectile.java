package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.math.BigDecimal;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Projectile extends GameEntity {
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal speedX;
	private BigDecimal speedY;
		
	public Projectile(Integer x, Integer y, Integer speedX, Integer speedY, Integer width, Integer height) {
		super();
		this.x = BigDecimal.valueOf(x);
		this.y = BigDecimal.valueOf(y);
		this.width = BigDecimal.valueOf(width);
		this.height = BigDecimal.valueOf(height);
		this.speedX = BigDecimal.valueOf(speedX);
		this.speedY = BigDecimal.valueOf(speedY);
	}
	
	public Projectile() {
		
	}
	
	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)){
			g.setColor(Color.RED);
			g.fillOval(getGraphicsX(v), getGraphicsY(v), getWidthInteger(), getHeightInteger());
		}
	}

	public void update(int delta) {
		//System.out.println(delta);
		x = x.add(speedX.multiply(BigDecimal.valueOf(delta / 1000.0)));
		y = y.add(speedY.multiply(BigDecimal.valueOf(delta / 1000.0)));
	
		speedY = speedY.subtract(BigDecimal.valueOf(Constants.gravAcc * (delta / 1000.0)));
		
		speedX = speedX.subtract(BigDecimal.valueOf(speedX.signum() * getHeightInteger()/10.0 * (delta / 1000.0)));
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

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public BigDecimal getSpeedX() {
		return speedX;
	}

	public void setSpeedX(BigDecimal speedX) {
		this.speedX = speedX;
	}

	public BigDecimal getSpeedY() {
		return speedY;
	}

	public void setSpeedY(BigDecimal speedY) {
		this.speedY = speedY;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}	

}
