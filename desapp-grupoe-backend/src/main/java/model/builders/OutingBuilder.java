package model.builders;

import model.Outing;
import model.OutingTag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OutingBuilder {

    public static OutingBuilder anyOuting() {
        return new OutingBuilder();
    }

    private String name = "Outing X";
    private List<OutingTag> tags = new ArrayList<>();
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private double price = 1.0;

    public Outing build() {
        Outing outing = new Outing(name, tags, date, time, price);
        return outing;
    }

    public OutingBuilder withName(String aName) {
        name = aName;
        return this;
    }

    public OutingBuilder withTags(ArrayList<OutingTag> listOfTags) {
        tags = listOfTags;
        return this;
    }

    public OutingBuilder withDate(LocalDate ld) {
        date = ld;
        return this;
    }

    public OutingBuilder withTime(LocalTime lt) {
        time = lt;
        return this;
    }

    public OutingBuilder withPrice(double p) {
        price = p;
        return this;
    }


}
