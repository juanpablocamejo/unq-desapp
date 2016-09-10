package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Outing implements IOuting {

    private long id;
    private String name;
    private List<OutingTag> tags = new ArrayList<OutingTag>();
    private LocalDate date;
    private LocalTime time;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
