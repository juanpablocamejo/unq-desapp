package model.outings;

import model.Entity;
import model.locations.Address;
import model.tags.Tag;
import model.time.TimeSlot;
import model.users.User;
import org.joda.time.LocalDate;
import persistence.strategies.IPlanningResult;

import java.util.ArrayList;
import java.util.List;

public abstract class Outing extends Entity implements IPlanningResult {

    private String name;
    private String description;
    private String image;
    private Address address;
    private List<Tag> tags = new ArrayList<>();
    private List<User> assistants = new ArrayList<>();
    private int maxAssistants;
    private double price;

    protected Outing(String name, String description, String image, Address address, List<Tag> tags, List<User> assistants, int maxAssistants, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.address = address;
        this.tags = tags;
        this.assistants = assistants;
        this.maxAssistants = maxAssistants;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getMaxAssistants() {
        return maxAssistants;
    }

    public void setMaxAssistants(int maxAssistants) {
        this.maxAssistants = maxAssistants;
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
        boolean hasTag = false;
        for (int i = 0; i < getTags().size(); i++) {
            if (getTags().get(i).getId() == tag.getId()) {
                hasTag = true;
                break;
            }
        }
        if (!hasTag) {
            getTags().add(tag);
        }
    }

    public void addAssistant(User user) {
        boolean hasAssistant = false;
        for (int i = 0; i < getAssistants().size(); i++) {
            if (getAssistants().get(i).getId() == user.getId()) {
                hasAssistant = true;
                break;
            }
        }
        if (!hasAssistant) {
            getAssistants().add(user);
        }
    }

    public void removeAssistant(User user) {
        for (int i = 0; i < getAssistants().size(); i++) {
            if (getAssistants().get(i).getId() == user.getId()) {
                getAssistants().remove(i);
            }
        }
    }

    public boolean matchWith(List<Tag> list) {
        boolean result = false;
        for (Tag tag : getTags()) {
            for (Tag listTag : list) {
                if (tag.equals(listTag)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
