package be.howest.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import be.howest.difficulty.Difficulty;
import be.howest.util.DataConnection;

public class getDifficultyTest {

	@Test
	public void test() {
		DataConnection database = DataConnection.getInstance();
        
        Difficulty difficulty = database.getDifficulty();
        	System.out.println(difficulty.toString());
        	System.out.println(difficulty.getSpeed());
	}

}
