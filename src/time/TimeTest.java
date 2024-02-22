package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	public void testGetTotalSecondsGood()
	{
		int seconds = Time.getTotalSeconds("05:05:05:005");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,()-> {Time.getTotalSeconds("10:00");});
	}
	@Test
	void testGetTotalSecondsBoundary() {
		int num = Time.getTotalSeconds("23:59:59:999");
		assertTrue("The seconds were not calculated properly", num == 86399);
	}
	
	//--------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(strings = { "05:10:15:020", "23:59:59:999" })
	void testGetSecondsGoodBoundary(String candidate) {
		int seconds = Time.getSeconds(candidate);
		if (candidate.equals("05:10:15:020"))
			assertTrue("The seconds were not extracted properly", seconds == 15);
		else
			assertTrue("The seconds were not extracted properly", seconds == 59);
	}
	@Test
	void testGetSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,()-> {Time.getSeconds("10:00");});
	}
	
	//--------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(strings = { "05:10:15:020", "23:59:59:999" })
	void testGetTotalMintuesGoodBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		if (candidate.equals("05:10:15:020"))
			assertTrue("The minutes were not calculated properly", minutes == 10);
		else
			assertTrue("The minutes were not calculated properly", minutes == 59);
	}
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class,()-> {Time.getTotalMinutes("10");});
	}
	
	//--------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(strings = { "05:10:15:020", "23:59:59:999" })
	void testGetTotalHoursGoodBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		if (candidate.equals("05:10:15:020"))
			assertTrue("The hours were not calculated properly", hours == 05);
		else
			assertTrue("The hours were not calculated properly", hours == 23);
	}
	@Test
	void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class,()-> {Time.getTotalHours("hello");});
	}
	
	//--------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(strings = { "12:05:05:006", "23:59:59:999" })
	void testGetMillisecondsGoodBoundary(String candidate) {
		int milliseconds = Time.getMilliseconds(candidate);
		if (candidate.equals("12:05:05:006"))
			assertTrue("The milliseconds were not calculated properly", milliseconds == 006);
		else
			assertTrue("The milliseconds were not calculated properly", milliseconds == 999);
	}
	@Test
	void testGetmillisecondsBad() {
		int milliseconds = Time.getMilliseconds("12:12:12");
		assertTrue("The milliseconds was functional", milliseconds == -1);
	}

}
