package model.planning;

import model.outings.Outing;
import model.outings.OutingTag;
import model.time.TimeSlot;
import org.joda.time.LocalDate;

public class OutingFilter {
    private LocalDate date;
    private TimeSlot timeSlot;
    private OutingTag searchTag;
    private double maxPrice;

    public OutingFilter(LocalDate date, TimeSlot timeSlot, OutingTag searchTag, double maxPrice) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.searchTag = searchTag;
        this.maxPrice = maxPrice;
    }

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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean match(Outing outing) {
        return matchPrice(outing)
                && matchDateTime(outing)
                && matchSearchTag(outing);
    }

    private boolean matchDateTime(Outing outing) {
        if (timeSlot != null)
            return date == null || outing.matchWith(date, timeSlot);
        else
            return date == null || outing.matchWith(date);
    }

    private boolean matchPrice(Outing outing) {
        return maxPrice == 0.0 || outing.matchWith(maxPrice);
    }

    private boolean matchSearchTag(Outing outing) {
        return searchTag == null || outing.matchWith(searchTag);
    }

}
