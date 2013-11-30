package sk.lchmelar.thegame.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.math.BigDecimal;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.graphics.Viewport;
import sk.lchmelar.thegame.interfaces.GameEntity;

public class Actor extends GameEntity {
	private BigDecimal x;
	private BigDecimal y;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal speedX;
	private BigDecimal speedY;	
	
	public Actor(Integer x, Integer y, Integer width, Integer height) {
		super();
		this.x = BigDecimal.valueOf(x);
		this.y = BigDecimal.valueOf(y);
		this.width = BigDecimal.valueOf(width);
		this.height = BigDecimal.valueOf(height);
		this.speedX = BigDecimal.valueOf(0);
		this.speedY = BigDecimal.valueOf(0);
	}
	
	@Override
	public void render(Graphics2D g, Viewport v) {
		if (isVisibleToViewport(v)){
			g.setColor(Color.BLUE);
			g.fillRect(getGraphicsX(v), getGraphicsY(v), getWidthInteger(), getHeightInteger());
		}
	}
	
	public void changeSpeedX(BigDecimal value) {
		speedX = speedX.add(value);
	}
	
	public void changeSpeedY(BigDecimal value) {
		speedY = speedY.add(value);
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		x = x.add(speedX.multiply(BigDecimal.valueOf(delta / 1000.0)));
		y = y.add(speedY.multiply(BigDecimal.valueOf(delta / 1000.0)));
	
		//speedY = speedY.subtract(BigDecimal.valueOf(Constants.gravAcc * (delta / 1000.0)));
		
		speedX = speedX.subtract(BigDecimal.valueOf(speedX.signum() * getHeightInteger() * (delta / 1000.0)));
		speedY = speedY.subtract(BigDecimal.valueOf(speedY.signum() * getWidthInteger() * (delta / 1000.0)));
	}
	
	public Projectile shoot(Integer targetX, Integer targetY) {
		Integer vectorX = targetX - getXInteger();
		Integer vectorY = targetY - getYInteger();
		
		System.out.println(vectorX + " " + vectorY);
		
		double length = Math.sqrt(vectorX*vectorX + vectorY*vectorY);
		
		System.out.println(length);
		
		Projectile p = new Projectile();
		
		p.setX(getX());
		p.setY(getY());
		System.out.println((vectorX / length ));
		
		p.setSpeedX(BigDecimal.valueOf((vectorX / length ) * 300));
		p.setSpeedY(BigDecimal.valueOf((vectorY / length ) * 300));
		
		p.setWidth(BigDecimal.valueOf(3));
		p.setHeight(BigDecimal.valueOf(3));
		
		return p;
		
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
