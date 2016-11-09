package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.difficulty.Difficulty;
import be.howest.game.Game;
import be.howest.game.ID;
import be.howest.objects.Player;
import be.howest.objects.SlowTime;

public class SlowTimeTest {

	@Test
	public void test() {

		//test difficulty
		Difficulty difficulty = new Difficulty("Easy", 1, 1, 1, 1, 1);
		//test Player
		Player player = new Player(200,200,ID.Player,3);
		//test PowerUp
		SlowTime slowtime = new SlowTime("Slow Time",1);

		
		slowtime.usePower(player, difficulty);
		System.out.print(difficulty.getSpeed());
		
	}

}
