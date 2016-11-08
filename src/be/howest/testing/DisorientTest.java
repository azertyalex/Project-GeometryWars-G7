package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Player;
import be.howest.objects.Disorient;

public class DisorientTest {

	@Test
	public void test() {

		//test Player
		Player player = new Player(200,200,ID.Player,3);
		//test PowerUp
		Disorient disorient = new Disorient("Disorient",1);
		//test Game
		Game game = new Game();
				
		disorient.usePower(player);
		game.run();
	}

}
