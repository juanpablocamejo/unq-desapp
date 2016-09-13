package model.planning;

import model.outings.OutingTag;
import model.time.TimeSlot;
import org.joda.time.LocalDate;

import java.util.List;

public class OutingFilter {
    private LocalDate date;
    private TimeSlot timeSlot;
    private OutingTag searchTag;
    private List<OutingTag> profileTags;
    private int maxPrice;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public OutingTag getSearchTag() {
        return searchTag;
    }

    public void setSearchTag(OutingTag searchTag) {
        this.searchTag = searchTag;
    }

    public List<OutingTag> getProfileTags() {
        return profileTags;
    }

    public void setProfileTags(List<OutingTag> profileTags) {
        this.profileTags = profileTags;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
