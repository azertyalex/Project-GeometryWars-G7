package be.howest.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import be.howest.game.Game;
import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.game.Game.STATE;
import be.howest.input.InputHandler;
import be.howest.input.PlayerMove;
import be.howest.input.PlayerShoot;
import be.howest.objects.Drone;
import be.howest.objects.testObject;

public class Play extends InputHandler implements UserInterface {

	private Game game;
	private Handler handler;
	private Hud HUD;
	private boolean isAdded = false;

	public Play(Game game, Handler handler, Hud hud) {
		this.game = game;
		this.handler = handler;
		this.HUD = hud;

	}

	@Override
	public void mouseAction(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		HUD.render(g);
		handler.render(g);
	}

	private void resetGame() {
		isAdded = false;
		System.out.println(handler.getList());

		// TEMP -- DEMO PURPOSE -- REMOVE THIS LATER
		handler.clearObject();
		Game.playerObjects.clear();
		Game.enemyObjects.clear();
		Game.hud.clear();

		game.removeMouseListener(game.getMouse());
		game.removeMouseMotionListener(game.getMouse());

		game.addAllObjects();
		// TEMP -- ...
	}

	@Override
	public void tick() {
		if (!isAdded) {
			// Player
			if (Game.CONTROLLER) {
				Game.playerObjects.add(new testObject(Game.WIDTH / 2, Game.HEIGHT / 2, 50, 50, ID.Player2, ID.Player,
						handler, game.getGamepad(), 3));
			} else {
				Game.playerObjects.add(
						new testObject(Game.WIDTH / 2, Game.HEIGHT / 2, 50, 50, ID.Player2, ID.Player, handler, 3));
			}
			// Drone
			Game.playerObjects.add(new Drone(0, 0, 40, 40, ID.Drone, handler));

			handler.addObject(Game.enemyObjects);
			handler.addObject(Game.playerObjects);
			handler.addObject(Game.hud);

			game.setMouse(new PlayerShoot(handler, handler.getGameObject(ID.Player2)));
			game.addKeyListener(new PlayerMove(handler));
			game.addMouseListener(game.getMouse());
			game.addMouseMotionListener(game.getMouse());

			isAdded = true;

		} else {
			System.out.println(game.getState());
			HUD.setHudHealth(Game.playerObjects.get(0).getHealth());
			// state = (playerObjects.get(0).getHealth() > 0)? STATE.PLAY :
			// STATE.GAME_OVER;
			if ((Game.playerObjects.get(0).getHealth() <= 0)) {
				game.setState(STATE.GAME_OVER);
				resetGame();
			} else if (!handler.hasEnemies()) {
				game.setState(STATE.VICTORY);
				resetGame();
			} else {
				game.setState(STATE.PLAY);
			}
		}

	}
}
