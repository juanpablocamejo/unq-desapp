package model.time;

import org.joda.time.LocalTime;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeSlotTest {
    @Test
    public void includesShouldReturnTrueWithIncludedSlotAndFalseWithNotIncludedSlot() throws Exception {
        LocalTime start = LocalTime.parse("8:00");
        LocalTime end = LocalTime.parse("18:00");
        TimeSlot firstTimeSlot = new TimeSlot(start, end);
        TimeSlot includedInFirstTimeSlot = new TimeSlot(start.plusMinutes(1), end.minusMinutes(1));

        assertTrue(firstTimeSlot.includes(includedInFirstTimeSlot));
        assertFalse(includedInFirstTimeSlot.includes(firstTimeSlot));
    }

    @Test
    public void includesShouldReturnTrueWithIncludedTimeAndFalseWithNotIncludedTime() throws Exception {
        TimeSlot timeSlot = new TimeSlot(LocalTime.parse("8:00"), LocalTime.parse("18:00"));

        assertTrue(timeSlot.includes(LocalTime.parse("9:00")));
        assertFalse(timeSlot.includes(LocalTime.parse("19:00")));
    }

}