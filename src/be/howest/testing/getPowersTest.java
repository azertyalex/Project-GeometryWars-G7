package be.howest.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import be.howest.objects.Powers;
import be.howest.util.DataConnection;

public class getPowersTest {

	@Test
	public void test() {
		DataConnection database = DataConnection.getInstance();
        
		List<Powers> powerList = database.getPowers();
		
		for(int i = 0; i < powerList.size(); i++){
			System.out.println(powerList.get(i));
		}
	}
}

