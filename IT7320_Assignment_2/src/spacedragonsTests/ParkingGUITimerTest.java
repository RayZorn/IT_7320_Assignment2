package spacedragonsTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import spacedragons.ParkingGUI;

public class ParkingGUITimerTest {
	
	double startingTime;
	double endingTime;
	double timerTime;
	ParkingGUI parkingGUI;

	@Before
	public void setUp() throws Exception 
	{
		parkingGUI = new ParkingGUI();
		
		startingTime = (double) System.currentTimeMillis();
		
		parkingGUI.startTimer(true);		
						
		Thread.sleep(3000);		
		endingTime = (double) System.currentTimeMillis();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() 
	{
		timerTime = parkingGUI.getCurrentTime() / 10;		
		assertEquals("Timer Test 1", 3, timerTime, 0.2);
		
		System.out.println("System time " + ((endingTime - startingTime) / 1000));		
		double systemTime = (endingTime - startingTime) / 1000;		
		assertEquals("Timer Test 2", systemTime, timerTime, 0.2);
		
		//fail("Not yet implemented");
	}

}
