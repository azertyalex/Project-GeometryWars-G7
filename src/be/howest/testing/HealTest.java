package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.difficulty.Difficulty;
import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Heal;
import be.howest.objects.Player;

public class HealTest {

	@Test
	public void test() {

		//test Player
		Player player = new Player(200,200,ID.Player,1);
		//test PowerUp
		Heal heal = new Heal("Heal", 1);
		//test Game
		Game game = new Game();
		
		heal.usePower(player);
		System.out.println(player.getHealth());
	}

}
