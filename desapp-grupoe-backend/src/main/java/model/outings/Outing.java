package model.outings;

import model.planning.IPlanningResult;
import model.time.TimeSlot;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public abstract class Outing implements IPlanningResult {
    private String name;
    private String description;
    private List<OutingTag> tags = new ArrayList<>();
    private double price;

    protected Outing(String name, String description, List<OutingTag> tags, double price) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OutingTag> getTags() {
        return tags;
    }

    public void setTags(List<OutingTag> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean isEvent();

    public abstract boolean isPack();

    public abstract boolean isPlace();

    public abstract boolean matchWith(LocalDate date);

    public abstract boolean matchWith(LocalDate date, TimeSlot timeSlot);

    public boolean matchWith(double maxPrice) {
        return maxPrice >= price;
    }

    public boolean matchWith(OutingTag tag) {
        return tags.contains(tag);
    }
}
