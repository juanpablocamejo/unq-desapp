package model.time;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class DayTimeSchedule {
    private int weekDay;
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public DayTimeSchedule(int weekDay, List<TimeSlot> timeSlots) {
        this.weekDay = weekDay;
        this.timeSlots = timeSlots;
    }

    public DayTimeSchedule() {
    }

    public DayTimeSchedule(int weekDay, TimeSlot timeSlot) {
        this.weekDay = weekDay;
        this.timeSlots.add(timeSlot);
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void addTimeSlot(LocalTime start, LocalTime end) throws DuplicatedTimeSlotException {
        checkConflicts(start, end);
        timeSlots.add(new TimeSlot(start, end));
    }

    public void addTimeSlot(TimeSlot timeSlot) throws DuplicatedTimeSlotException {
        addTimeSlot(timeSlot.getStart(), timeSlot.getEnd());
    }

    private void checkConflicts(LocalTime start, LocalTime end) throws DuplicatedTimeSlotException {
        for (TimeSlot slot : timeSlots) {
            if (slot.includes(start) || slot.includes(end)) {
                throw new DuplicatedTimeSlotException("Error: existing timeSlot includes especified time");
            }
        }
    }

    public boolean includes(LocalDate date, TimeSlot timeSlot) {
        return weekDay == date.getDayOfWeek() && includes(timeSlot);
    }

    public boolean includes(TimeSlot timeSlot) {
        for (TimeSlot slot : timeSlots) {
            if (slot.includes(timeSlot)) {
                return true;
            }
        }
        return false;
    }


    public boolean includes(LocalDate date) {
        return weekDay == date.getDayOfWeek();
    }
}
