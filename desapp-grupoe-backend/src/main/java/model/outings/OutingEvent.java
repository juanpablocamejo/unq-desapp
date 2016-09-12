package model.outings;

import org.joda.time.LocalDateTime;

import java.util.List;

public class OutingEvent extends Outing {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    protected OutingEvent(String name, String description, List<OutingTag> tags, double price, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name, description, tags, price);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean isEvent() {
        return true;
    }

    @Override
    public boolean isPack() {
        return false;
    }

    @Override
    public boolean isPlace() {
        return false;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
