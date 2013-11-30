package sk.lchmelar.thegame.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.entities.Projectile;

public class GameInputListener implements KeyListener, MouseListener {
	private GraphicsRenderer graphicsRenderer;
	
	private int lastPressedX;
	private int lastPressedY;
	
	private int lastReleasedX;
	private int lastReleasedY;
	
	public GameInputListener(GraphicsRenderer graphicsRenderer) {
		this.graphicsRenderer = graphicsRenderer;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			//System.out.println("D");
			if(graphicsRenderer.getViewport().getX() + graphicsRenderer.getViewport().getWidth() + Constants.cameraSpeed <= Constants.centerX*2) {
				graphicsRenderer.getViewport().setX(graphicsRenderer.getViewport().getX() + Constants.cameraSpeed);
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			//System.out.println("A");
			if(graphicsRenderer.getViewport().getX() - Constants.cameraSpeed >= 0) {
				graphicsRenderer.getViewport().setX(graphicsRenderer.getViewport().getX() - Constants.cameraSpeed);
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			//System.out.println("W");
			if(graphicsRenderer.getViewport().getY() - Constants.cameraSpeed >= 0) {
				graphicsRenderer.getViewport().setY(graphicsRenderer.getViewport().getY() - Constants.cameraSpeed);
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			//System.out.println("S");
			if(graphicsRenderer.getViewport().getY() + graphicsRenderer.getViewport().getHeight() + Constants.cameraSpeed <= Constants.centerY*2) {
				graphicsRenderer.getViewport().setY(graphicsRenderer.getViewport().getY() + Constants.cameraSpeed);
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		//System.out.println("Halo");
	}

	public void keyTyped(KeyEvent arg0) {
		//System.out.println("Halo");
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		lastPressedX = e.getX();
		lastPressedY = e.getY();		
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("released");
		lastReleasedX = e.getX();
		lastReleasedY = e.getY();		
		graphicsRenderer.getGameWorld().addEntity(new Projectile(graphicsRenderer.getViewport().getX() + lastPressedX, graphicsRenderer.getViewport().getY() + lastPressedY, lastReleasedX - lastPressedX, lastReleasedY - lastPressedY, 10, 10));				
	}
}
