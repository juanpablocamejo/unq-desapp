package model.builders.outings;

import model.tags.Tag;
import model.planning.OutingFilter;
import model.time.TimeSlot;
import org.joda.time.LocalDate;

public class OutingFilterBuilder {

    private LocalDate date = null;
    private TimeSlot timeSlot = null;
    private Tag searchTag = null;
    private double maxPrice = 0;


    public static OutingFilterBuilder anOutingFilter() {
        return new OutingFilterBuilder();
    }

    public OutingFilter build() {
        return new OutingFilter(date, timeSlot, searchTag, maxPrice);
    }

    public OutingFilterBuilder withDate(LocalDate d) {
        date = d;
        return this;
    }

    public OutingFilterBuilder withDateTime(LocalDate d, TimeSlot ts) {
        date = d;
        timeSlot = ts;
        return this;
    }

    public OutingFilterBuilder withSearchTag(Tag tag) {
        searchTag = tag;
        return this;
    }

    public OutingFilterBuilder withMaxPrice(double p) {
        maxPrice = p;
        return this;
    }


}
