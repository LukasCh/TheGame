package sk.lchmelar.thegame.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import sk.lchmelar.thegame.TheGame;
import sk.lchmelar.thegame.config.Constants;
import sk.lchmelar.thegame.entities.GameWorld;

public class GraphicsRenderer extends Thread {
	private boolean isRunning = true;
	private Canvas canvas;
	private BufferStrategy strategy;
	private BufferedImage background;
	private Graphics2D backgroundGraphics;
	private Graphics2D graphics;
	private JFrame frame;
	private int width = 800;
	private int height = 800;
	
	public static long debugLastRenderDelta;
	
	private Viewport viewport;
	
	private GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	private GameWorld gameWorld;
	
	DecimalFormat df = new DecimalFormat("#.00");
		
	// create a hardware accelerated image
	private final BufferedImage create(final int width, final int height, final boolean alpha) {
		return config.createCompatibleImage(width, height, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
	}

	// Setup
	public GraphicsRenderer(GameWorld gameWorld) {
		// JFrame
		frame = new JFrame();
		frame.addWindowListener(new FrameClose());	
		
		
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setSize(width + 16, height + 38);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setFocusable(true);
		frame.requestFocus();

		// Canvas
		canvas = new Canvas(config);
		GameInputListener gil = new GameInputListener(this);
		canvas.addKeyListener(gil);
		canvas.addMouseListener(gil);
		
		canvas.setSize(width, height);
		frame.add(canvas, 0);

		// Background & Buffer
		background = create(width, height, false);
		canvas.createBufferStrategy(2);
		do {
			strategy = canvas.getBufferStrategy();
		} while (strategy == null);
		
		// Game Logic related stuff
		this.gameWorld = gameWorld;
		
		viewport = new Viewport(Constants.centerX/Constants.precision - width/2, Constants.centerY/Constants.precision - height/2, width, height);
		
		start();
	}

	private class FrameClose extends WindowAdapter {
		public void windowClosing(final WindowEvent e) {
			isRunning = false;
			TheGame.isRunning = false;
		}
	}

	// Screen and buffer stuff
	private Graphics2D getBuffer() {
		if (graphics == null) {
			try {
				graphics = (Graphics2D) strategy.getDrawGraphics();
			} catch (IllegalStateException e) {
				return null;
			}
		}
		return graphics;
	}

	private boolean updateScreen() {
		graphics.dispose();
		graphics = null;
		try {
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			return (!strategy.contentsLost());

		} catch (NullPointerException e) {
			return true;

		} catch (IllegalStateException e) {
			return true;
		}
	}

	public void run() {
		backgroundGraphics =  background.createGraphics();
		long fpsWait = (long) 10;
		long lastRender = System.currentTimeMillis();
		if(TheGame.debugMode) debugLastRenderDelta = lastRender;
		main: while (isRunning) {			
			// Update Graphics
			do {
				Graphics2D bg = getBuffer();
				if (!isRunning) {
					break main;
				}
				long delta = System.currentTimeMillis() - lastRender;  
				if(TheGame.debugMode) debugLastRenderDelta = delta;
				lastRender = System.currentTimeMillis();			
				renderGame(backgroundGraphics, viewport); 
						
				
				
				/*if (scale != 1) {
					bg.drawImage(background, cameraX.intValue(), cameraY.intValue(), width * scale, height * scale, 0, 0, width, height, null);
				} else {*/
				bg.drawImage(background, 0, 0, width, height, 0, 0, viewport.getWidth(), viewport.getHeight() ,null);
				//System.out.println(cameraX + width/2 - (cameraX - width/2));
				/*}*/
				bg.dispose();
			} while (!updateScreen());

			// Better do some FPS limiting here
			long totalRenderTime = System.currentTimeMillis() - lastRender;
			try {
				Thread.sleep(Math.max(0, fpsWait - totalRenderTime));
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
		}
		frame.dispose();
	}
	
	private void renderGame(Graphics2D g, Viewport v) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, v.getWidth(), v.getHeight());
		
		if (TheGame.isRunning) {
			drawDebugDisplay(g, v);
		}
		gameWorld.render(g, v);
	}
	
	private Map<String, Object> getDebugInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("viewport", viewport);
		result.put("isRunning", isRunning);
		result.put("graphicsFPS", df.format(1000.0/debugLastRenderDelta));
		//System.out.println(debugLastRenderDelta);
		result.put("physicsFPS", df.format(1000.0/TheGame.debugLastUpdateDelta));
		//System.out.println(TheGame.debugLastUpdateDelta);
		result.put("entityCount", gameWorld.getEntities().size());
		result.put("playerSpeed", gameWorld.getEntities().get(1).getSpeedX() + "," + gameWorld.getEntities().get(1).getSpeedY());
		
		return result;
	}
	
	private void drawDebugDisplay(Graphics2D g, Viewport v) {		
		int x = 5;
		int y = 0;
		int rowHeight = 15;
		g.setColor(Color.BLACK);
		
		Map<String, Object> debugInfo = getDebugInfo();
		//debugInfo.size()
		for (Entry<String, Object> entry : debugInfo.entrySet()) {
			g.drawString(entry.getKey() + " = " + entry.getValue(), x , y+=rowHeight);
			//System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}	
}