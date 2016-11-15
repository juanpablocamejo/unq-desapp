package model.outings;

import model.locations.Address;
import model.tags.Tag;
import model.time.TimeSlot;
import model.time.WeekTimeSchedule;
import model.users.User;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.LocalDate;
import persistence.strategies.IPlanningResult;

import java.util.List;

public class OutingPlace extends Outing implements IPlanningResult {
    private WeekTimeSchedule weekTimeSchedule;

    public OutingPlace() {
    }

    public OutingPlace(String name, String description, String image, Address address, List<Tag> tags, List<User> assistants, int maxAssistants, double price, WeekTimeSchedule weekTimeSchedule) {
        super(name, description, image, address, tags, assistants, maxAssistants, price);
        this.weekTimeSchedule = weekTimeSchedule;
    }

    @JsonIgnore
    @Override
    public boolean isEvent() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isPack() {
        return false;
    }

    @JsonIgnore
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
