package model.time;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class WeekTimeSchedule {
    private List<DayTimeSchedule> schedules = new ArrayList<>();

    public List<DayTimeSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<DayTimeSchedule> schedules) {
        this.schedules = schedules;
    }

    public void addTimeSlot(int weekDay, LocalTime start, LocalTime end) throws DuplicatedTimeSlotException {
        for (DayTimeSchedule schedule : schedules) {
            if (schedule.getWeekDay() == weekDay) {
                schedule.addTimeSlot(start, end);
                return;
            }
        }
        schedules.add(new DayTimeSchedule(weekDay, new TimeSlot(start, end)));
    }

    public void addTimeSlot(int weekDay, LocalTime start, int hours) throws DuplicatedTimeSlotException {
        addTimeSlot(weekDay, start, start.plusHours(hours));
    }

    public void addTimeSlot(int weekDay, LocalTime start, int hours, int minutes) throws DuplicatedTimeSlotException {
        addTimeSlot(weekDay, start, start.plusHours(hours).plusMinutes(minutes));
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, LocalTime end) throws DuplicatedTimeSlotException {
        for (Integer i : weekDays)
            addTimeSlot(i, start, end);
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, int hours) throws DuplicatedTimeSlotException {
        for (Integer i : weekDays)
            addTimeSlot(i, start, hours);
    }

    public void addTimeSlots(List<Integer> weekDays, LocalTime start, int hours, int minutes) throws DuplicatedTimeSlotException {
        for (Integer i : weekDays)
            addTimeSlot(i, start, hours, minutes);
    }

    public int getDaysCount() {
        return schedules.size();
    }

    public boolean includes(LocalDate date, TimeSlot time) {
        for (DayTimeSchedule dts : schedules) {
            if (dts.includes(date, time)) {
                return true;
            }
        }
        return false;
    }

    public boolean includes(LocalDate date) {
        for (DayTimeSchedule dts : schedules) {
            if (dts.includes(date)) {
                return true;
            }
        }
        return false;
    }
}
