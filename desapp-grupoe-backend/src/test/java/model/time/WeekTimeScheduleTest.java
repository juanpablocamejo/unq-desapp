package model.time;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;


import static junit.framework.TestCase.*;
import static model.builders.DayTimeScheduleBuilder.anyDayTimeSchedule;
import static model.builders.TimeSlotBuilder.anyTimeSlot;

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

    @Test
    public void testIncludesGoesOKWhenIncluidesAnExistingDateAndTime() throws Exception {

        TimeSlot ts1 = anyTimeSlot().withStart(LocalTime.MIDNIGHT).withEnd(LocalTime.MIDNIGHT.plusHours(4)).build();
        TimeSlot ts2 = anyTimeSlot().withStart(LocalTime.MIDNIGHT).withEnd(LocalTime.MIDNIGHT.plusHours(6)).build();
        DayTimeSchedule dayTimeSchedule = anyDayTimeSchedule().withWeekDay(6).withTimeSlot(ts1).withTimeSlot(ts2).build();

        assertTrue(dayTimeSchedule.includes(LocalDate.now(), ts1));
        assertTrue(dayTimeSchedule.includes(LocalDate.now(), ts2));
        assertTrue(dayTimeSchedule.includes(LocalDate.now(), anyTimeSlot().withStart(LocalTime.MIDNIGHT.plusHours(1)).withEnd(LocalTime.MIDNIGHT.plusHours(6)).build()));
    }


}