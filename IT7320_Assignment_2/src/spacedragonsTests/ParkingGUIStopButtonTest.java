package spacedragonsTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spacedragons.ParkingGUI;

class ParkingGUIStopButtonTest {
	
	double timerTime;
	Boolean running = true;
	ParkingGUI parkingGUI;

	@BeforeEach
	void setUp() throws Exception 
	{
		parkingGUI = new ParkingGUI();
		
		parkingGUI.startTimer(true);							
		
		Thread.sleep(3000);		
		
		parkingGUI.stopTimer();
		
		Thread.sleep(3000);
		
		running = parkingGUI.isTimerRunning();
		
		timerTime = parkingGUI.getCurrentTime() / 10;
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() 
	{
		assertFalse("Stop Btn Test 1", running);

		assertEquals("Stop Btn Test 2", 3, timerTime, 0.2);
		
		//fail("Not yet implemented");
	}

}
