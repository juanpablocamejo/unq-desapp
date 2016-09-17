package model.builders;

import model.time.DayTimeSchedule;
import model.time.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class DayTimeScheduleBuilder {

    private int weekDay = 5;
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public static DayTimeScheduleBuilder anyDayTimeSchedule() {
        return new DayTimeScheduleBuilder();
    }

    public DayTimeSchedule build() {
        return new DayTimeSchedule(weekDay, timeSlots);
    }

    public DayTimeScheduleBuilder withWeekDay(int d) {
        weekDay = d;
        return this;
    }

    public DayTimeScheduleBuilder withTimeSlot(TimeSlot ts) {
        timeSlots.add(ts);
        return this;
    }
}
