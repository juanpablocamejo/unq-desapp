package model.time;

import junit.framework.TestCase;
import org.joda.time.LocalTime;
import org.junit.Test;

public class TimeSlotTest extends TestCase {
    @Test
    public void testIncludesShouldReturnTrueWithIncludedSlotAndFalseWithNotIncludedSlot() throws Exception {
        LocalTime start = LocalTime.parse("8:00");
        LocalTime end = LocalTime.parse("18:00");
        TimeSlot firstTimeSlot = new TimeSlot(start, end);
        TimeSlot includedInFirstTimeSlot = new TimeSlot(start.plusMinutes(1), end.minusMinutes(1));

        assertTrue(firstTimeSlot.includes(includedInFirstTimeSlot));
        assertFalse(includedInFirstTimeSlot.includes(firstTimeSlot));
    }

    @Test
    public void testIncludesShouldReturnTrueWithIncludedTimeAndFalseWithNotIncludedTime() throws Exception {
        TimeSlot timeSlot = new TimeSlot(LocalTime.parse("8:00"), LocalTime.parse("18:00"));

        assertTrue(timeSlot.includes(LocalTime.parse("9:00")));
        assertFalse(timeSlot.includes(LocalTime.parse("19:00")));
    }

}