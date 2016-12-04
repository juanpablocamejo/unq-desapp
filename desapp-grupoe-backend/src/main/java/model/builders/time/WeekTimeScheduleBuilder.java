package model.builders.time;

import model.time.DayTimeSchedule;
import model.time.TimeSlot;
import model.time.WeekTimeSchedule;

import java.util.ArrayList;
import java.util.List;

public class WeekTimeScheduleBuilder {

    private List<DayTimeSchedule> schedules = new ArrayList<>();

    public static WeekTimeScheduleBuilder anyWeekTimeSchedule() {
        return new WeekTimeScheduleBuilder();
    }

    public WeekTimeSchedule build() {
        return new WeekTimeSchedule(schedules);
    }

    public WeekTimeScheduleBuilder addDayTimeSchedule(DayTimeSchedule d) {
        schedules.add(d);
        return this;
    }

    public WeekTimeScheduleBuilder withSchedules(List<DayTimeSchedule> list) {
        schedules = list;
        return this;
    }

    public WeekTimeScheduleBuilder withDayAndTimeSlot(int day, TimeSlot t) {
        schedules.add(new DayTimeSchedule(day, t));
        return this;
    }

}
