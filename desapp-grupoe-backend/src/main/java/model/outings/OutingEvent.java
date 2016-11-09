package model.outings;

import model.locations.Address;
import model.tags.Tag;
import model.time.TimeSlot;
import model.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

public class OutingEvent extends Outing {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public OutingEvent() {
        //super();

    }

    public OutingEvent(String name, String description, Address address, List<Tag> tags, List<User> assistants, double price) {
        super(name, description, address, tags, assistants, price);
    }

    public OutingEvent(String name, String description, Address address, List<Tag> tags, List<User> assistants, double price, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name, description, address, tags, assistants, price);
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

    @Override
    public boolean matchWith(LocalDate date) {
        return startDateTime == null || date.compareTo(startDateTime.toLocalDate()) == 0;
    }

    @Override
    public boolean matchWith(LocalDate date, TimeSlot time) {
        return matchWith(date)
                && (startDateTime == null || time.includes(startDateTime.toLocalTime()))
                && (endDateTime == null || time.includes(endDateTime.toLocalTime()));
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
