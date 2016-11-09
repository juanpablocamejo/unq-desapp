package model.builders.outings;

import model.builders.AddressBuilder;
import model.locations.Address;
import model.outings.OutingPlace;
import model.tags.Tag;
import model.time.DuplicatedTimeSlotException;
import model.time.WeekTimeSchedule;
import model.users.User;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class OutingPlaceBuilder {

    private String name = "Name";
    private String description = "Description";
    private Address address = AddressBuilder.anyAddress().build();
    private List<Tag> tags = new ArrayList<>();
    private List<User> assistants = new ArrayList<>();
    private double price = 1;
    private WeekTimeSchedule weekTimeSchedule = new WeekTimeSchedule();

    public static OutingPlaceBuilder anOutingPlace() {
        return new OutingPlaceBuilder();
    }

    public OutingPlace build() {
        return new OutingPlace(name, description, address, tags, assistants, price, weekTimeSchedule);
    }

    public OutingPlaceBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingPlaceBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingPlaceBuilder withAddress(Address a) {
        address = a;
        return this;
    }

    public OutingPlaceBuilder withTags(ArrayList<Tag> t) {
        tags = t;
        return this;
    }

    public OutingPlaceBuilder withTag(Tag t) {
        tags.add(t);
        return this;
    }

    public OutingPlaceBuilder withAssistants(ArrayList<User> a) {
        assistants = a;
        return this;
    }

    public OutingPlaceBuilder withAssistant(User u) {
        assistants.add(u);
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
