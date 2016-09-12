package model.time;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class WeekTimeSchedule {
    private List<DayTimeSchedule> schedules = new ArrayList<>();

    public void addTimeSlot(int weekDay, LocalTime start, LocalTime end) {
        for (DayTimeSchedule schedule : schedules) {
            if (schedule.getWeekDay() == weekDay) {
                schedule.addTimeSlot(start, end);
                return;
            }
        }
        schedules.add(new DayTimeSchedule(weekDay, new TimeSlot(start, end)));
    }

    public void addTimeSlot(int weekDay, LocalTime start, int hours) {
        addTimeSlot(weekDay, start, start.plusHours(hours));
    }

    public void addTimeSlot(int weekDay, LocalTime start, int hours, int minutes) {
        addTimeSlot(weekDay, start, start.plusHours(hours).plusMinutes(minutes));
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, LocalTime end) {
        for (Integer i : weekDays)
            addTimeSlot(i, start, end);
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, int hours) {
        for (Integer i : weekDays)
            addTimeSlot(i, start, hours);
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, int hours, int minutes) {
        for (Integer i : weekDays)
            addTimeSlot(i, start, hours, minutes);
    }

    public int getDaysCount() {
        return schedules.size();
    }
}
