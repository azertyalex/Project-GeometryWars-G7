package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Revive;
import be.howest.objects.Player;

public class ReviveTest {

	@Test
	public void test() {
		
		//test Player 1
		Player player1 = new Player(200,200,ID.Player,2);
		//test Player 2
		Player player2 = new Player(300,300,ID.Player,0);
		//test PowerUp
		Revive revive = new Revive("Revive", 1);
		//test Game
		Game game = new Game();
		
		System.out.println("Before Revive: ");
		System.out.println("Player is alive: " + player2.checkIfAlive());
		System.out.println(player2.getHealth());
		System.out.println("---------------------");
		revive.usePower(player2);
		System.out.println("After Revive: ");
		System.out.println("Player is alive: " + player2.checkIfAlive());
		System.out.println(player2.getHealth());
	}

}
