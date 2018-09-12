package spacedragonsTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HomeTest.class, LoginTest.class, ParkedTest.class, ParkingGUITest.class, ParkingTest.class,
		ParkingTimerTest.class, RegisterTest.class, RetrieveTest.class, TermsTest.class })
public class AllTests {

}
