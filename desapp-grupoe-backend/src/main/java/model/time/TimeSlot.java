package model.time;

import org.joda.time.LocalTime;

public class TimeSlot {
    private LocalTime start;
    private LocalTime end;

    public TimeSlot(LocalTime start, int minutes) {
        this.start = start;
        this.end = start.plusMinutes(minutes);
    }

    public TimeSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public TimeSlot() {
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean includes(TimeSlot timeSlot) {
        return timeSlot.start.compareTo(this.start) >= 0 && timeSlot.end.compareTo(this.end) <= 0;
    }

    public boolean includes(LocalTime time) {
        return start.compareTo(time) <= 0 && end.compareTo(time) >= 0;
    }
}
