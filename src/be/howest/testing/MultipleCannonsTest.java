package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.MultipleCannons;
import be.howest.objects.Player;

public class MultipleCannonsTest {

	@Test
	public void test() {
		
		//test Player
		Player player = new Player(200,200,ID.Player,1);
		//test PowerUp
		MultipleCannons cannons = new MultipleCannons("MultiCannon", 1);
		//test Game
		Game game = new Game();
		
		cannons.usePower(player);
		System.out.println(player.getCannonAmount());
	}

}
