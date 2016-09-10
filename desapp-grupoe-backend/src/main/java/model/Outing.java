package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Outing implements IOuting {

    private long id;
    private String name;
    private List<OutingTag> tags = new ArrayList<>();
    private LocalDate date;
    private LocalTime time;
    private double price;

    public Outing(String name, List<OutingTag> tags, LocalDate date, LocalTime time, double price) {
        this.name = name;
        this.tags = tags;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
