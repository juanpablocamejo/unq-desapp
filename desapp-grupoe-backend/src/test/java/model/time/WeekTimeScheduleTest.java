package model.time;

import junit.framework.TestCase;
import org.joda.time.LocalTime;
import org.junit.Test;

public class WeekTimeScheduleTest extends TestCase {
    private LocalTime anyLocalTime() {
        return LocalTime.now();
    }

    @Test
    public void testAddTimeSlotPassingStartAndEndLocalTimes() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();
        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, time1);
        schedule.addTimeSlot(1, time1.plusHours(1), time1.plusHours(1));
        assertEquals(schedule.getDaysCount(), 1);
    }

    @Test
    public void testAddTimeSlotPassingStartLocalTimesAndHours() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();
        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, 1);
        assertEquals(schedule.getDaysCount(), 1);
    }

    @Test
    public void testAddTimeSlotPassingStartLocalTimesHoursAndMinutes() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();

        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, 1, 30);
        assertEquals(schedule.getDaysCount(), 1);
    }

}