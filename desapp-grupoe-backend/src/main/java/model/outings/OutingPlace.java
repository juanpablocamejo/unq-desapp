package model.outings;

import model.planning.IPlanningResult;
import model.time.WeekTimeSchedule;

import java.util.List;

public class OutingPlace extends Outing implements IPlanningResult {
    private WeekTimeSchedule weekTimeSchedule;

    protected OutingPlace(String name, String description, List<OutingTag> tags, double price, WeekTimeSchedule weekTimeSchedule) {
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
}
