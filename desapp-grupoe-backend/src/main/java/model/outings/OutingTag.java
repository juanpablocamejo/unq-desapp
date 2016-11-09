package model.outings;

import model.Entity;

public class OutingTag extends Entity {
    private String name;

    public OutingTag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutingTag outingTag = (OutingTag) o;

        return getName() == outingTag.getName();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public OutingTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
