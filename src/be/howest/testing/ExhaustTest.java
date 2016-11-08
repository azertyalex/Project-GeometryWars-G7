package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.difficulty.Difficulty;
import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Player;
import be.howest.objects.Exhaust;

public class ExhaustTest {

	@Test
	public void test() {
		
		//test Player
		Player player = new Player(200,200,ID.Player,3);
		//test PowerUp
		Exhaust exhaust = new Exhaust("Exhaust", 1);
		Game game = new Game();
				
		
		exhaust.usePower(player);
		game.run();
		
	}

}
