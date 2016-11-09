package model.builders.outings;

import model.builders.AddressBuilder;
import model.locations.Address;
import model.outings.Outing;
import model.outings.OutingEvent;
import model.tags.Tag;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public class OutingBuilder {

    private String name = "Name";
    private String description = "Description";
    private Address address = AddressBuilder.anyAddress().build();
    private List<Tag> tags = new ArrayList<>();
    private List<User> assistants = new ArrayList<>();
    private double price = 1;

    public static OutingBuilder anyOuting() {
        return new OutingBuilder();
    }

    public Outing build() {
        return new OutingEvent(name, description, address, tags, assistants, price);
    }

    public OutingBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingBuilder withAddress(Address a) {
        address = a;
        return this;
    }

    public OutingBuilder withTags(ArrayList<Tag> t) {
        tags = t;
        return this;
    }

    public OutingBuilder withTag(Tag t) {
        tags.add(t);
        return this;
    }

    public OutingBuilder withAssistants(ArrayList<User> a) {
        assistants = a;
        return this;
    }

    public OutingBuilder withAssistant(User u) {
        assistants.add(u);
        return this;
    }

    public OutingBuilder withPrice(double p) {
        price = p;
        return this;
    }

}
