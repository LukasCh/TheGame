package sk.lchmelar.thegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.entities.Actor;
import sk.lchmelar.thegame.graphics.GraphicsRenderer;

public class GameInputListener implements KeyListener, MouseListener, MouseMotionListener {
	private GameInputManager gameInputManager;	
	
	public GameInputListener(GameInputManager gameInputManager) {
		this.gameInputManager = gameInputManager;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameInputManager.pressKey(GameInputManager.CAMERA_RIGHT);
		} else 
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			gameInputManager.pressKey(GameInputManager.CAMERA_LEFT);
		} else 
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			gameInputManager.pressKey(GameInputManager.CAMERA_UP);
		} else
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			gameInputManager.pressKey(GameInputManager.CAMERA_DOWN);
		} else		
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			gameInputManager.pressKey(GameInputManager.UP);
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			gameInputManager.pressKey(GameInputManager.DOWN);	
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			gameInputManager.pressKey(GameInputManager.LEFT);
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			gameInputManager.pressKey(GameInputManager.RIGHT);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameInputManager.releaseKey(GameInputManager.CAMERA_RIGHT);
		} else 
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			gameInputManager.releaseKey(GameInputManager.CAMERA_LEFT);
		} else 
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			gameInputManager.releaseKey(GameInputManager.CAMERA_UP);
		} else
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			gameInputManager.releaseKey(GameInputManager.CAMERA_DOWN);
		} else		
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			gameInputManager.releaseKey(GameInputManager.UP);
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			gameInputManager.releaseKey(GameInputManager.DOWN);	
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_A) {
			gameInputManager.releaseKey(GameInputManager.LEFT);
		} else	
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			gameInputManager.releaseKey(GameInputManager.RIGHT);
		}
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
		gameInputManager.setMouseX(e.getX());
		gameInputManager.setMouseY(e.getY());
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			gameInputManager.pressKey(GameInputManager.LMB);
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			gameInputManager.pressKey(GameInputManager.MMB);
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			gameInputManager.pressKey(GameInputManager.RMB);
		}		
	}

	public void mouseReleased(MouseEvent e) {
		gameInputManager.setMouseX(e.getX());
		gameInputManager.setMouseY(e.getY());
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			gameInputManager.releaseKey(GameInputManager.LMB);
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			gameInputManager.releaseKey(GameInputManager.MMB);
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			gameInputManager.releaseKey(GameInputManager.RMB);
		}
	}

	public void mouseDragged(MouseEvent e) {
		gameInputManager.setMouseX(e.getX());
		gameInputManager.setMouseY(e.getY());
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			gameInputManager.releaseKey(GameInputManager.LMB);
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			gameInputManager.releaseKey(GameInputManager.MMB);
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			gameInputManager.releaseKey(GameInputManager.RMB);
		}		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
