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
        
		database.getPowers();
		
		/*for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}*/
	}
}

