package model.builders;

import model.outings.Outing;
import model.outings.OutingPlace;
import model.outings.OutingTag;
import model.time.DuplicatedTimeSlotException;
import model.time.WeekTimeSchedule;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class OutingPlaceBuilder {

    private String name = "Name";
    private String description = "Description";
    private List<OutingTag> tags = new ArrayList<>();
    private double price = 1;
    private WeekTimeSchedule weekTimeSchedule = new WeekTimeSchedule();

    public static OutingPlaceBuilder anOutingPlace() {
        return new OutingPlaceBuilder();
    }

    public Outing build() {
        return new OutingPlace(name, description, tags, price, weekTimeSchedule);
    }

    public OutingPlaceBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingPlaceBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingPlaceBuilder withTags(ArrayList<OutingTag> t) {
        tags = t;
        return this;
    }

    public OutingPlaceBuilder withTag(OutingTag t) {
        tags.add(t);
        return this;
    }

    public OutingPlaceBuilder withPrice(double p) {
        price = p;
        return this;
    }

    public OutingPlaceBuilder withWeekTimeSchedule(WeekTimeSchedule weekSchedule) {
        weekTimeSchedule = weekSchedule;
        return this;
    }

    public OutingPlaceBuilder withTimeSlot(int dayOfWeek, LocalTime startTime, LocalTime endTime) throws DuplicatedTimeSlotException {
        weekTimeSchedule.addTimeSlot(dayOfWeek, startTime, endTime);
        return this;
    }


}
