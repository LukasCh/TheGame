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
			long delta = System.currentTimeMillis() - lastUpdate;  
			if(debugMode) debugLastUpdateDelta = delta;
			lastUpdate = System.currentTimeMillis();
			updateGame(delta);		
			
			long totalUpdateTime = System.currentTimeMillis() - lastUpdate;
			try {
				Thread.sleep(Math.max(0, fpsWait - totalUpdateTime));
			} catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
		}		
	}	
}
