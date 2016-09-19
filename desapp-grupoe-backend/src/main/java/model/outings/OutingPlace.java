package model.outings;

import model.planning.IPlanningResult;
import model.time.TimeSlot;
import model.time.WeekTimeSchedule;
import org.joda.time.LocalDate;

import java.util.List;

public class OutingPlace extends Outing implements IPlanningResult {
    private WeekTimeSchedule weekTimeSchedule;

    public OutingPlace(String name, String description, List<OutingTag> tags, double price, WeekTimeSchedule weekTimeSchedule) {
        super(name, description, tags, price);
        this.weekTimeSchedule = weekTimeSchedule;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isPack() {
        return false;
    }

    @Override
    public boolean isPlace() {
        return true;
    }

    @Override
    public boolean matchWith(LocalDate date) {
        return weekTimeSchedule.includes(date);
    }

    @Override
    public boolean matchWith(LocalDate date, TimeSlot timeSlot) {
        return weekTimeSchedule.includes(date, timeSlot);
    }

    public WeekTimeSchedule getWeekTimeSchedule() {
        return weekTimeSchedule;
    }

    public void setWeekTimeSchedule(WeekTimeSchedule weekTimeSchedule) {
        this.weekTimeSchedule = weekTimeSchedule;
    }
}
