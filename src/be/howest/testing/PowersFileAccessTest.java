package be.howest.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import be.howest.objects.Barrier;
import be.howest.objects.Clone;
import be.howest.objects.Heal;
import be.howest.objects.Powers;
import be.howest.objects.SlowTime;
import be.howest.util.PowersFileAccess;

public class PowersFileAccessTest {

	@Test
	public void test() {
		Powers power1 = new Barrier("Barrier", 1);
		Powers power2 = new Heal("Heal", 1);
		Powers power3 = new SlowTime("SlowTime", 1);
		Powers power4 = new Clone("Clone", 1);
		
		List<Powers> powerList = new ArrayList<>();
		
		powerList.add(power1);
		powerList.add(power2);
		powerList.add(power3);
		powerList.add(power4);
		
		
		
		String fileName = "PowersTest.txt";
		PowersFileAccess pfa = new PowersFileAccess();
		pfa.save(powerList, fileName);
		
		pfa.load(fileName);
	}

}
