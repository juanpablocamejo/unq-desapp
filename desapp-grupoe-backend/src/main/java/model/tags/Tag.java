package model.tags;

import model.Entity;

public class Tag extends Entity {
    private String name;
    private TagCategory category;

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, TagCategory category) {
        this.name = name;
        this.category=category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return getName() == tag.getName();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagCategory getCategory() {
        return category;
    }

    public void setCategory(TagCategory category) {
        this.category = category;
    }
}
