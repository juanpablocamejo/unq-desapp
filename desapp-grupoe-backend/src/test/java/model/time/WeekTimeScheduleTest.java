package model.time;

import org.joda.time.LocalTime;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class WeekTimeScheduleTest {
    private LocalTime anyLocalTime() {
        return LocalTime.now();
    }

    @Test
    public void addTimeSlotPassingStartAndEndLocalTimes() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();
        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, time1);
        schedule.addTimeSlot(1, time1.plusHours(1), time1.plusHours(1));
        assertEquals(schedule.getDaysCount(), 1);
    }

    @Test
    public void addTimeSlotPassingStartLocalTimesAndHours() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();
        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, 1);
        assertEquals(schedule.getDaysCount(), 1);
    }

    @Test
    public void addTimeSlotPassingStartLocalTimesHoursAndMinutes() throws Exception {
        WeekTimeSchedule schedule = new WeekTimeSchedule();
        LocalTime time1 = anyLocalTime();

        assertEquals(schedule.getDaysCount(), 0);
        schedule.addTimeSlot(1, time1, 1, 30);
        assertEquals(schedule.getDaysCount(), 1);
    }

}