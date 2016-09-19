package model.builders;

import model.outings.Outing;
import model.outings.OutingEvent;
import model.outings.OutingTag;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class OutingEventBuilder {

    private String name = "Name";
    private String description = "Description";
    private List<OutingTag> tags = new ArrayList<>();
    private double price = 1;
    private LocalDateTime startDateTime = LocalDateTime.now();
    private LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);

    public static OutingEventBuilder anOutingEvent() {
        return new OutingEventBuilder();
    }

    public Outing build() {
        return new OutingEvent(name, description, tags, price, startDateTime, endDateTime);
    }

    public OutingEventBuilder withName(String n) {
        name = n;
        return this;
    }

    public OutingEventBuilder withDescription(String d) {
        description = d;
        return this;
    }

    public OutingEventBuilder withTags(ArrayList<OutingTag> t) {
        tags = t;
        return this;
    }

    public OutingEventBuilder withTag(OutingTag t) {
        tags.add(t);
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
