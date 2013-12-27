package sk.lchmelar.thegame;


import sk.lchmelar.thegame.entities.GameWorld;
import sk.lchmelar.thegame.graphics.GraphicsRenderer;
import sk.lchmelar.thegame.input.GameInputListener;
import sk.lchmelar.thegame.input.GameInputManager;

public class TheGame {
	public static boolean isRunning = true;
	public static boolean debugMode = true;
	public static long debugLastUpdateDelta;
	private GameWorld gameWorld;
	private GraphicsRenderer graphicsRenderer;
	private GameInputManager gameInputManager;
	
	public TheGame() {
		gameWorld = new GameWorld();
		graphicsRenderer = new GraphicsRenderer(gameWorld, new GameInputListener(gameInputManager = new GameInputManager(this)));
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
			gameInputManager.handleInput();
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

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public GraphicsRenderer getGraphicsRenderer() {
		return graphicsRenderer;
	}

	public void setGraphicsRenderer(GraphicsRenderer graphicsRenderer) {
		this.graphicsRenderer = graphicsRenderer;
	}	
}
