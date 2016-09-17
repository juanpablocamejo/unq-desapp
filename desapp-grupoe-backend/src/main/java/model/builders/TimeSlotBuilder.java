package model.builders;

import model.time.TimeSlot;
import org.joda.time.LocalTime;

public class TimeSlotBuilder {

    private LocalTime start = LocalTime.MIDNIGHT;
    private LocalTime end = LocalTime.MIDNIGHT.plusHours(12);

    public static TimeSlotBuilder anyTimeSlot() {
        return new TimeSlotBuilder();
    }

    public TimeSlot build() {
        return new TimeSlot(start, end);
    }

    public TimeSlotBuilder withStart(LocalTime time) {
        start = time;
        return this;
    }

    public TimeSlotBuilder withEnd(LocalTime time) {
        end = time;
        return this;
    }
}
