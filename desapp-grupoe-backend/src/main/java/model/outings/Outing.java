package model.outings;

import model.Entity;
import model.locations.Address;
import model.planning.IPlanningResult;
import model.tags.Tag;
import model.time.TimeSlot;
import model.users.User;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public abstract class Outing extends Entity implements IPlanningResult {

    private String name;
    private String description;
    private Address address;
    private List<Tag> tags = new ArrayList<>();
    private List<User> assistants = new ArrayList<>();
    private double price;

    protected Outing(String name, String description, Address address, List<Tag> tags, List<User> assistants, double price) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.tags = tags;
        this.assistants = assistants;
        this.price = price;
    }

    public Outing() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<User> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<User> assistants) {
        this.assistants = assistants;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract boolean isEvent();

    public abstract boolean isPack();

    public abstract boolean isPlace();

    public abstract boolean matchWith(LocalDate date);

    public abstract boolean matchWith(LocalDate date, TimeSlot timeSlot);

    public boolean matchWith(double maxPrice) {
        return maxPrice >= price;
    }

    public boolean matchWith(Tag tag) {
        return tags.contains(tag);
    }

    public void addTag(Tag tag) {
        if (!getTags().contains(tag)) {
            getTags().add(tag);
        }
    }

    public void addAssistant(User user) {
        if (!getAssistants().contains(user)) {
            getAssistants().add(user);
        }
    }

    public void removeAssistant(User user) {
        if (getAssistants().contains(user)) {
            getAssistants().remove(user);
        }
    }
}
