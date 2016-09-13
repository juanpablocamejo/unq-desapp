package model.builders;

import model.outings.OutingTag;
import model.planning.OutingFilter;
import model.time.TimeSlot;
import org.joda.time.LocalDate;

import java.util.List;

public class OutingFilterBuilder {

    private LocalDate date = null;
    private TimeSlot timeSlot = null;
    private OutingTag searchTag = null;
    private List<OutingTag> profileTags = null;
    private double maxPrice = 0;


    public static OutingFilterBuilder anOutingFilter() {
        return new OutingFilterBuilder();
    }

    public OutingFilter build() {
        return new OutingFilter(date, timeSlot, searchTag, profileTags, maxPrice);
    }

    public OutingFilterBuilder withDate(LocalDate d) {
        date = d;
        return this;
    }

    public OutingFilterBuilder withTimeSlot(TimeSlot ts) {
        timeSlot = ts;
        return this;
    }

    public OutingFilterBuilder withSearchTag(OutingTag tag) {
        searchTag = tag;
        return this;
    }

    public OutingFilterBuilder withProfileTags(List<OutingTag> tags) {
        profileTags = tags;
        return this;
    }

    public OutingFilterBuilder withMaxPrice(double p) {
        maxPrice = p;
        return this;
    }


}
