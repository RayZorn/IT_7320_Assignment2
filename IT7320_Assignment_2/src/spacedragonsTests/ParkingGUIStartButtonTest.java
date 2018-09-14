package spacedragonsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spacedragons.ParkingGUI;

public class ParkingGUIStartButtonTest {
	
	double timerTimeStart;
	double timerTimeEnd;
	Boolean running = true;
	ParkingGUI parkingGUI;

	@BeforeEach
	void setUp() throws Exception {
		parkingGUI = new ParkingGUI();
		
		parkingGUI.startTimer(true);		
						
		timerTimeStart = parkingGUI.getCurrentTime() / 10;
		
		running = parkingGUI.isTimerRunning();
		
		Thread.sleep(3000);		
		
		timerTimeEnd = parkingGUI.getCurrentTime() / 10;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		assertTrue("Stop Btn Test 1", running);

		assertNotEquals("Stop Btn Test 2", timerTimeEnd, timerTimeStart, 2.8);
		
		//fail("Not yet implemented");
	}

}
