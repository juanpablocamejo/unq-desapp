package model.builders;

import model.outings.Outing;
import model.outings.OutingEvent;
import model.outings.OutingTag;

import java.util.ArrayList;
import java.util.List;

public class OutingBuilder {

    private String name = "Name";
    private String description = "Description";
    private List<OutingTag> tags = new ArrayList<>();
    private double price = 1;

    public static OutingBuilder anyOuting() {
        return new OutingBuilder();
    }

    public Outing build() {
        return new OutingEvent(name, description, tags, price);
    }

    public OutingBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingBuilder withTags(ArrayList<OutingTag> t) {
        tags = t;
        return this;
    }

    public OutingBuilder withTag(OutingTag t) {
        tags.add(t);
        return this;
    }

    public OutingBuilder withPrice(double p) {
        price = p;
        return this;
    }

}
