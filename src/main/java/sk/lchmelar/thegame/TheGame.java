package sk.lchmelar.thegame;


import sk.lchmelar.thegame.entities.GameWorld;
import sk.lchmelar.thegame.graphics.GraphicsRenderer;

public class TheGame {
	public static boolean isRunning = true;
	public static boolean debugMode = true;
	public static long debugLastUpdateDelta;
	private GameWorld gameWorld;
	//private GraphicsRenderer graphicsRenderer;
	
	public TheGame() {
		gameWorld = new GameWorld();
		new GraphicsRenderer(gameWorld);
	}
	
	public void updateGame(long delta) {
		//if(debugMode) System.out.println(delta);
		gameWorld.update((int) delta);
	}
	
	public void run() {
		long fpsWait = (long) 10;
		long lastUpdate = System.currentTimeMillis();
		if(debugMode) debugLastUpdateDelta = lastUpdate;
		while (isRunning) {
			long updateStart = System.nanoTime();
			long delta = System.currentTimeMillis() - lastUpdate;  
			updateGame(delta);
			lastUpdate = System.currentTimeMillis();			
			if(debugMode) debugLastUpdateDelta = delta;
			
			
			long renderTime = (System.nanoTime() - updateStart) / 1000000;
			try {
				Thread.sleep(Math.max(0, fpsWait - renderTime));
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
			renderTime = (System.nanoTime() - updateStart) / 1000000;
		}		
	}	
}
