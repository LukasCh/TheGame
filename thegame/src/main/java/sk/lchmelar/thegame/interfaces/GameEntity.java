package sk.lchmelar.thegame.interfaces;

import java.awt.Graphics2D;
import java.math.BigDecimal;
import sk.lchmelar.thegame.graphics.Viewport;

public abstract class GameEntity {
	public abstract void render(Graphics2D g, Viewport v);
	public abstract void update(int delta);
	public abstract BigDecimal getX();
	public abstract BigDecimal getY();
	public abstract BigDecimal getWidth();
	public abstract BigDecimal getHeight();
	
	protected boolean isVisibleToViewport(Viewport v){
		if(this.getX().intValue() - this.getWidth().intValue()/2 >= v.getX() 
		|| this.getX().intValue() + this.getWidth().intValue()/2 <= v.getX() + v.getWidth()
		|| this.getY().intValue() - this.getHeight().intValue()/2 >= v.getY() 
		|| this.getY().intValue() + this.getHeight().intValue()/2 <= v.getY() + v.getHeight()) {
			return true;
		}
		return false;
	}
	
	protected Integer getGraphicsX(Viewport v){
		return (int) Math.round(getXInteger() - getWidthInteger()/2.0 - v.getX()); 
	}
	
	protected Integer getGraphicsY(Viewport v){
		return (int) Math.round(getYInteger() - getHeightInteger()/2.0 - v.getY()); 
	}
	
	public Integer getXInteger() {
		return (int) (Math.round(getX().doubleValue()));
	}
	
	public Integer getYInteger() {
		return (int) (Math.round(getY().doubleValue()));
	}
	
	public Integer getWidthInteger() {
		return (int) (Math.round(getWidth().doubleValue()));
	}
	
	public Integer getHeightInteger() {
		return (int) (Math.round(getHeight().doubleValue()));
	}
}
