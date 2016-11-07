package be.howest.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import be.howest.difficulty.Difficulty;
import be.howest.game.ID;
import be.howest.objects.Player;

public class SlowTimeTest {

	@Test
	public void test() {

		//test difficulty
		Difficulty difficulty = new Difficulty("Easy", 1, 1, 1, 1, 1);
		//test Player
		Player player = new Player(200,200,ID.Player);
	}

}
