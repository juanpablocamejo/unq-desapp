package model.builders.outings;

import model.builders.AddressBuilder;
import model.locations.Address;
import model.outings.OutingEvent;
import model.tags.Tag;
import model.users.User;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class OutingEventBuilder {

    private String name = "Name";
    private String description = "Description";
    private Address address = AddressBuilder.anyAddress().build();
    private List<Tag> tags = new ArrayList<>();
    private List<User> assistants = new ArrayList<>();
    private double price = 1;
    private LocalDateTime startDateTime = LocalDateTime.now();
    private LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);

    public static OutingEventBuilder anOutingEvent() {
        return new OutingEventBuilder();
    }

    public OutingEvent build() {
        return new OutingEvent(name, description, address, tags, assistants, price, startDateTime, endDateTime);
    }

    public OutingEventBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingEventBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingEventBuilder withAddress(Address a) {
        address = a;
        return this;
    }

    public OutingEventBuilder withTags(ArrayList<Tag> t) {
        tags = t;
        return this;
    }

    public OutingEventBuilder withTag(Tag t) {
        tags.add(t);
        return this;
    }

    public OutingEventBuilder withAssistants(ArrayList<User> a) {
        assistants = a;
        return this;
    }

    public OutingEventBuilder withAssistant(User u) {
        assistants.add(u);
        return this;
    }

    public OutingEventBuilder withPrice(double p) {
        price = p;
        return this;
    }

    public OutingEventBuilder withStartDateTime(LocalDateTime datetime) {
        startDateTime = datetime;
        return this;
    }


}
