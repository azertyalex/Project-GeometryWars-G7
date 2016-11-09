package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Player;
import be.howest.objects.testObject;
import be.howest.objects.Blackout;
import be.howest.objects.GameObject;

public class BlackoutTest  {

	@Test
	public void test() {

		//test handler
		//test Player

		//test PowerUp
		Blackout bo = new Blackout("Blackout",1);
		//test game
		Game game = new Game();
		GameObject player =  game.handler.getGameObject(ID.Player2);
		player.setHealth(3);
		

		
		bo.usePower(player);
		game.run();
		
	}

}
