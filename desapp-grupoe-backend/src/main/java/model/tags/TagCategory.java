package model.tags;

import model.Entity;

public class TagCategory extends Entity {
    private String name;

    public TagCategory() {
    }

    public TagCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
