package model.time;

import junit.framework.TestCase;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class DayTimeScheduleTest extends TestCase {
    private TimeSlot anyTimeSlot() {
        return new TimeSlot(LocalTime.parse("9:00"), LocalTime.parse("10:00"));
    }

    private LocalDate anyLocalDate() {
        return LocalDate.now();
    }

    @Test
    public void testAScheduleShouldHaveSlotsAfterAddATimeSlot() throws Exception {
        DayTimeSchedule schedule = new DayTimeSchedule();
        assertTrue(schedule.getTimeSlots().isEmpty());
        schedule.addTimeSlot(this.anyTimeSlot());
        assertFalse(schedule.getTimeSlots().isEmpty());
    }

    @Test
    public void testAddATimeSlotShouldRaiseExceptionWhenAddAnExistingSlot() throws Exception {
        DayTimeSchedule schedule = new DayTimeSchedule();
        TimeSlot timeSlot1 = anyTimeSlot();
        schedule.addTimeSlot(timeSlot1);
        try {
            schedule.addTimeSlot(timeSlot1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Error: existing timeslot includes especified time");
        }
    }

    @Test
    public void testIncludesShouldReturnTrueWhenPassADateWithAnIncludedWeekDayAndSlot() {
        DayTimeSchedule schedule = new DayTimeSchedule();
        TimeSlot timeSlot1 = anyTimeSlot();
        LocalDate date1 = anyLocalDate();
        schedule.setWeekDay(date1.getDayOfWeek());
        schedule.addTimeSlot(timeSlot1);

        assertTrue(schedule.includes(date1, timeSlot1));
        assertFalse(schedule.includes(date1.plusDays(1), timeSlot1));
    }

}