package rest.dto;

import model.Entity;

import java.util.List;

public class UserDTO extends Entity {
    private String name;
    private String surname;
    private String[] address;
    private List<Integer> tags;
    private List<String> friends;
    private double inexpensiveOutingLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public double getInexpensiveOutingLimit() {
        return inexpensiveOutingLimit;
    }

    public void setInexpensiveOutingLimit(double inexpensiveOutingLimit) {
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }

    public UserDTO() {

    }
}
