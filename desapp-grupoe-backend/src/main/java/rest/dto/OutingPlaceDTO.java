package rest.dto;

import model.Entity;

import java.util.ArrayList;
import java.util.List;

public class OutingPlaceDTO extends Entity {

    private String name;
    private String description;
    private String image;
    private String[] address;
    private List<Integer> tags = new ArrayList<>();
    private List<String> assistants = new ArrayList<>();
    private int maxAssistants;
    private double price;
    private String weekTimeSchedule;

    public OutingPlaceDTO() {
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

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<String> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<String> assistants) {
        this.assistants = assistants;
    }

    public int getMaxAssistants() {
        return maxAssistants;
    }

    public void setMaxAssistants(int maxAssistants) {
        this.maxAssistants = maxAssistants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeekTimeSchedule() {
        return weekTimeSchedule;
    }

    public void setWeekTimeSchedule(String weekTimeSchedule) {
        this.weekTimeSchedule = weekTimeSchedule;
    }
}
