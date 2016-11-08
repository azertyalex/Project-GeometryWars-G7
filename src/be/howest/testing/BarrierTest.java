package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Barrier;
import be.howest.objects.Player;

public class BarrierTest {

	@Test
	public void test() {
		
		//test Player
		Player player = new Player(200,200,ID.Player,1);
		//test PowerUp
		Barrier barrier = new Barrier("Barrier", 1);
		//test Game
		Game game = new Game();
		
		barrier.usePower(player);
		System.out.println(player.isBarrier());
	}

}
